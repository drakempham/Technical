# System.arraycopy() Under The Hood - Deep Dive

## 🔄 Complete Flow Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                      JAVA CODE                                  │
│                                                                 │
│  System.arraycopy(src, 0, dest, 0, 5);                        │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                   JAVA LAYER (System.java)                      │
│                                                                 │
│  public static native void arraycopy(                           │
│      Object src, int srcPos,                                    │
│      Object dest, int destPos,                                  │
│      int length                                                 │
│  );                                                             │
│                                                                 │
│  ❗ Keyword 'native' → Look for C/C++ implementation            │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                   JNI BRIDGE (jvm.cpp)                          │
│                                                                 │
│  JVM_ENTRY(void, JVM_ArrayCopy(JNIEnv *env, ...))              │
│  {                                                              │
│    // Convert Java objects to C++ objects                      │
│    oop src = JNIHandles::resolve(src_handle);                  │
│    oop dest = JNIHandles::resolve(dest_handle);                │
│                                                                 │
│    // Call actual implementation                               │
│    copy_array(...);                                             │
│  }                                                              │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│              VALIDATION LAYER (arraycopy.cpp)                   │
│                                                                 │
│  ┌─────────────────────────────────────────────────┐           │
│  │ 1. NULL CHECK                                   │           │
│  │    if (src == NULL || dest == NULL)             │           │
│  │        → throw NullPointerException              │           │
│  └─────────────────────────────────────────────────┘           │
│                          │                                      │
│  ┌─────────────────────────────────────────────────┐           │
│  │ 2. BOUNDS CHECK                                 │           │
│  │    if (srcPos < 0 || length < 0 || ...)         │           │
│  │        → throw ArrayIndexOutOfBoundsException    │           │
│  └─────────────────────────────────────────────────┘           │
│                          │                                      │
│  ┌─────────────────────────────────────────────────┐           │
│  │ 3. TYPE CHECK (for Object arrays)               │           │
│  │    if (!compatible_types(src, dest))            │           │
│  │        → throw ArrayStoreException               │           │
│  └─────────────────────────────────────────────────┘           │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────────┐
│                     TYPE ROUTING                                │
│                                                                 │
│              Is it primitive array?                             │
│                    │        │                                   │
│              YES   │        │   NO                              │
│         ┌──────────┘        └──────────┐                        │
│         ▼                               ▼                       │
│   ┌─────────────┐              ┌────────────────┐              │
│   │ FAST PATH   │              │   SLOW PATH    │              │
│   │ (memcpy)    │              │ (element copy) │              │
│   └─────────────┘              └────────────────┘              │
└────────┬──────────────────────────────┬──────────────────────────┘
         │                              │
         ▼                              ▼
┌─────────────────────┐      ┌──────────────────────────────────┐
│   FAST PATH         │      │      SLOW PATH                   │
│   (Primitive)       │      │      (Object Arrays)             │
│                     │      │                                  │
│ 1. Calculate addr   │      │ for (i = 0; i < length; i++) {  │
│    srcAddr = base + │      │                                  │
│    srcPos * size    │      │   // Get reference              │
│                     │      │   obj = src[srcPos + i];        │
│ 2. Overlap check    │      │                                  │
│    if overlap:      │      │   // Type check                 │
│      → memmove      │      │   if (!instanceof(obj, type))   │
│    else:            │      │      throw ArrayStoreException; │
│      → memcpy       │      │                                  │
│                     │      │   // Copy reference             │
│ 3. SIMD copy        │      │   dest[destPos + i] = obj;      │
│    __m256i v =      │      │                                  │
│    _mm256_load(src);│      │   // GC write barrier           │
│    _mm256_store     │      │   post_write_barrier(dest, i);  │
│    (dest, v);       │      │ }                                │
│                     │      │                                  │
│ // Copy 8 ints at  │      │ // Much slower!                  │
│ // once with AVX2!  │      │                                  │
└─────────────────────┘      └──────────────────────────────────┘
         │                              │
         └──────────────┬───────────────┘
                        ▼
┌─────────────────────────────────────────────────────────────────┐
│                   RETURN TO JAVA                                │
│                                                                 │
│  - Clean up JNI references                                      │
│  - Return void (or throw exception if error)                    │
│  - Resume Java bytecode execution                               │
└─────────────────────────────────────────────────────────────────┘
```

## 🎯 Key Optimizations Explained

### 1. SIMD (Single Instruction Multiple Data)

**Traditional Loop:**
```
┌───┬───┬───┬───┬───┬───┬───┬───┐
│ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │  Source
└───┴───┴───┴───┴───┴───┴───┴───┘

Copy one by one (8 operations):
dest[0] = src[0]  // Cycle 1
dest[1] = src[1]  // Cycle 2
dest[2] = src[2]  // Cycle 3
...
dest[7] = src[7]  // Cycle 8
```

**SIMD with AVX2:**
```
┌───────────────────────────────┐
│  256-bit register (32 bytes)  │
│  Holds 8 ints simultaneously  │
└───────────────────────────────┘

Load 8 ints at once:
__m256i vec = _mm256_loadu_si256(src);  // 1 operation!

Store 8 ints at once:
_mm256_storeu_si256(dest, vec);         // 1 operation!

Total: 2 operations instead of 16!
```

### 2. Memory Layout

**Primitive Array Memory Layout:**
```
┌──────────┬──────┬───┬───┬───┬───┬───┐
│ Header   │Length│ 1 │ 2 │ 3 │ 4 │ 5 │  int[]
└──────────┴──────┴───┴───┴───┴───┴───┘
                   ▲
                   Base address + offset
                   
Contiguous memory → Fast bulk copy!
```

**Object Array Memory Layout:**
```
┌──────────┬──────┬────┬────┬────┬────┐
│ Header   │Length│Ref1│Ref2│Ref3│Ref4│  Object[]
└──────────┴──────┴────┴────┴────┴────┘
                   │    │    │    │
                   ▼    ▼    ▼    ▼
              Objects in heap (scattered)

References only → Need individual copy + type checks
```

### 3. Overlap Handling

**Case 1: No overlap (use memcpy - faster)**
```
Source: [1][2][3][4][5]
Dest:                  [_][_][_][_][_]
        No overlap → Safe to use memcpy
```

**Case 2: Overlapping regions (use memmove - safe)**
```
Array: [1][2][3][4][5][_][_]
Copy:  [1][2][3] → [4][5][_]
              ↑──────↑
            Overlap! Must use memmove

memmove copies to temp buffer first:
1. temp ← src
2. dest ← temp
```

## 📊 Performance Breakdown

### Small Arrays (< 1000 elements)
- **Overhead dominates:** JNI call, validation checks
- **Speedup:** ~2-3x
- **Reason:** Setup cost is significant

### Medium Arrays (1,000 - 100,000)
- **SIMD kicks in:** AVX2/AVX512 instructions active
- **Speedup:** ~5-15x
- **Reason:** Vectorized operations shine

### Large Arrays (> 100,000)
- **Cache effects:** May hit cache limits
- **Speedup:** ~1.5-3x (cache misses reduce benefit)
- **Reason:** Memory bandwidth becomes bottleneck

## 🔧 Platform-Specific Implementations

### x86_64 (Intel/AMD)
- File: `stubGenerator_x86_64.cpp`
- Uses: SSE2, AVX2, AVX512 instructions
- Registers: 256-bit or 512-bit

### ARM64 (Apple Silicon, Mobile)
- File: `stubGenerator_aarch64.cpp`
- Uses: NEON SIMD instructions
- Registers: 128-bit

### Platform-agnostic
- Falls back to standard C memcpy/memmove
- Still faster than Java loops

## 💡 Why So Fast?

1. **Native Code:** No JVM overhead
2. **SIMD:** Copy multiple elements per instruction
3. **Cache-friendly:** Sequential memory access
4. **CPU Prefetching:** CPU predicts memory access pattern
5. **Loop Unrolling:** Compiler optimizations
6. **Memory Alignment:** Aligned access is faster
7. **JIT Intrinsics:** JIT recognizes and optimizes calls

## 🎓 Takeaways

- **Always prefer `System.arraycopy()`** for array copying
- It's **not just a loop** - it's heavily optimized native code
- For primitive arrays: **2-15x faster** than manual loops
- For object arrays: Still faster + handles type checking
- The larger the array, the bigger the performance gain (up to a point)



