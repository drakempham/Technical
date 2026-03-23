/**
 * System.arraycopy() Under the Hood
 * 
 * Giải thích chi tiết cách arraycopy hoạt động từ Java layer xuống native code
 */
public class ArrayCopyUnderTheHood {
    
    public static void main(String[] args) {
        System.out.println("=== System.arraycopy() Under the Hood ===\n");
        
        demonstrateSteps();
        demonstrateOptimizations();
        implementManualVersion();
    }
    
    /**
     * Các bước xử lý của System.arraycopy() từ high level xuống low level
     */
    public static void demonstrateSteps() {
        System.out.println("📋 STEP-BY-STEP PROCESS:\n");
        
        System.out.println("1️⃣  JAVA LAYER (System.java)");
        System.out.println("   - Method declaration: native void arraycopy(...)");
        System.out.println("   - Từ khóa 'native' → JVM sẽ tìm native implementation\n");
        
        System.out.println("2️⃣  JNI BRIDGE (Java Native Interface)");
        System.out.println("   - JVM lookup native function: JVM_ArrayCopy");
        System.out.println("   - Convert Java objects sang native pointers");
        System.out.println("   - File: jvm.cpp → JVM_ArrayCopy()\n");
        
        System.out.println("3️⃣  VALIDATION LAYER (arraycopy.cpp)");
        System.out.println("   a) NULL CHECK:");
        System.out.println("      if (src == null || dest == null)");
        System.out.println("         → throw NullPointerException");
        System.out.println();
        System.out.println("   b) BOUNDS CHECK:");
        System.out.println("      if (srcPos < 0 || destPos < 0 || length < 0)");
        System.out.println("         → throw ArrayIndexOutOfBoundsException");
        System.out.println("      if (srcPos + length > src.length)");
        System.out.println("         → throw ArrayIndexOutOfBoundsException");
        System.out.println();
        System.out.println("   c) TYPE CHECK (cho Object arrays):");
        System.out.println("      if (!dest.componentType.isAssignableFrom(src.componentType))");
        System.out.println("         → throw ArrayStoreException\n");
        
        System.out.println("4️⃣  ROUTING LOGIC (chọn implementation phù hợp)");
        System.out.println("   Phụ thuộc vào type của array:");
        System.out.println("   - Primitive arrays → Fast path (memcpy)");
        System.out.println("   - Object arrays → Slow path (cần type checking)\n");
        
        System.out.println("5️⃣  MEMORY COPY (Low-level implementation)\n");
        
        System.out.println("   📌 PATH A: PRIMITIVE ARRAYS (int[], byte[], etc.)");
        System.out.println("   ┌────────────────────────────────────────┐");
        System.out.println("   │ 1. Calculate memory addresses:        │");
        System.out.println("   │    srcAddr = srcArray + srcPos*size   │");
        System.out.println("   │    destAddr = destArray + destPos*size│");
        System.out.println("   │                                        │");
        System.out.println("   │ 2. Check for overlap:                 │");
        System.out.println("   │    if (src == dest && regions overlap)│");
        System.out.println("   │       → Use memmove (safe for overlap)│");
        System.out.println("   │    else                                │");
        System.out.println("   │       → Use memcpy (faster)           │");
        System.out.println("   │                                        │");
        System.out.println("   │ 3. SIMD Optimization (if available):  │");
        System.out.println("   │    - AVX2: 256-bit registers          │");
        System.out.println("   │    - AVX512: 512-bit registers        │");
        System.out.println("   │    → Copy 8-16 elements at once!      │");
        System.out.println("   │                                        │");
        System.out.println("   │ 4. Bulk memory copy:                  │");
        System.out.println("   │    memcpy(destAddr, srcAddr, bytes)   │");
        System.out.println("   └────────────────────────────────────────┘\n");
        
        System.out.println("   📌 PATH B: OBJECT ARRAYS (String[], Object[], etc.)");
        System.out.println("   ┌────────────────────────────────────────┐");
        System.out.println("   │ 1. Copy each reference individually:  │");
        System.out.println("   │    for (i = 0; i < length; i++) {     │");
        System.out.println("   │        Object obj = src[srcPos + i];  │");
        System.out.println("   │                                        │");
        System.out.println("   │        // Runtime type check          │");
        System.out.println("   │        if (!isInstanceOf(obj, destType))│");
        System.out.println("   │            throw ArrayStoreException; │");
        System.out.println("   │                                        │");
        System.out.println("   │        dest[destPos + i] = obj;       │");
        System.out.println("   │                                        │");
        System.out.println("   │        // GC Write Barrier            │");
        System.out.println("   │        notifyGC(dest, destPos + i);   │");
        System.out.println("   │    }                                   │");
        System.out.println("   └────────────────────────────────────────┘\n");
        
        System.out.println("6️⃣  RETURN TO JAVA");
        System.out.println("   - Clean up native references");
        System.out.println("   - Return control to Java code\n");
    }
    
    /**
     * Giải thích các optimizations
     */
    public static void demonstrateOptimizations() {
        System.out.println("\n🚀 OPTIMIZATIONS:\n");
        
        System.out.println("1. SIMD (Single Instruction Multiple Data)");
        System.out.println("   ┌─────────────────────────────────────────┐");
        System.out.println("   │ Traditional: Copy 1 element at a time  │");
        System.out.println("   │   int dest[0] = src[0];  // 1 cycle    │");
        System.out.println("   │   int dest[1] = src[1];  // 1 cycle    │");
        System.out.println("   │   int dest[2] = src[2];  // 1 cycle    │");
        System.out.println("   │   int dest[3] = src[3];  // 1 cycle    │");
        System.out.println("   │                                         │");
        System.out.println("   │ SIMD (AVX2): Copy 8 ints at once       │");
        System.out.println("   │   __m256i vec = _mm256_loadu_si256(src);│");
        System.out.println("   │   _mm256_storeu_si256(dest, vec);      │");
        System.out.println("   │   // 8 ints in 1 cycle! (8x faster)    │");
        System.out.println("   └─────────────────────────────────────────┘\n");
        
        System.out.println("2. CACHE-FRIENDLY MEMORY ACCESS");
        System.out.println("   - CPU prefetches sequential memory");
        System.out.println("   - Contiguous copy → better cache utilization");
        System.out.println("   - Reduces cache misses\n");
        
        System.out.println("3. LOOP UNROLLING");
        System.out.println("   - Process multiple elements per iteration");
        System.out.println("   - Reduces loop overhead");
        System.out.println("   - Better instruction pipeline usage\n");
        
        System.out.println("4. MEMORY ALIGNMENT");
        System.out.println("   - Align memory access to word boundaries");
        System.out.println("   - Faster access on most CPUs\n");
        
        System.out.println("5. JIT INTRINSICS");
        System.out.println("   - JIT compiler recognizes arraycopy");
        System.out.println("   - Can replace with optimized assembly");
        System.out.println("   - Platform-specific optimization\n");
    }
    
    /**
     * Manual implementation để hiểu logic (không optimize như native)
     */
    public static void implementManualVersion() {
        System.out.println("\n💻 MANUAL IMPLEMENTATION (Simplified):\n");
        
        int[] source = {1, 2, 3, 4, 5};
        int[] dest1 = new int[7];
        int[] dest2 = new int[7];
        
        // Native version
        System.arraycopy(source, 0, dest1, 1, 5);
        
        // Manual version
        manualArrayCopy(source, 0, dest2, 1, 5);
        
        System.out.println("Source: " + java.util.Arrays.toString(source));
        System.out.println("Native arraycopy result: " + java.util.Arrays.toString(dest1));
        System.out.println("Manual copy result:      " + java.util.Arrays.toString(dest2));
        System.out.println("Match: " + java.util.Arrays.equals(dest1, dest2));
        
        // Performance comparison
        comparePerformance();
    }
    
    /**
     * Manual implementation với all checks (như native version)
     */
    public static void manualArrayCopy(int[] src, int srcPos, int[] dest, int destPos, int length) {
        // 1. NULL checks
        if (src == null || dest == null) {
            throw new NullPointerException("Source or destination array is null");
        }
        
        // 2. Bounds checks
        if (srcPos < 0 || destPos < 0 || length < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative index or length");
        }
        
        if (srcPos + length > src.length) {
            throw new ArrayIndexOutOfBoundsException("Source array bounds exceeded");
        }
        
        if (destPos + length > dest.length) {
            throw new ArrayIndexOutOfBoundsException("Destination array bounds exceeded");
        }
        
        // 3. Copy elements
        // Native version uses optimized memory copy (memcpy/memmove)
        // This is simple loop (much slower than native)
        for (int i = 0; i < length; i++) {
            dest[destPos + i] = src[srcPos + i];
        }
    }
    
    /**
     * So sánh performance chi tiết
     */
    public static void comparePerformance() {
        System.out.println("\n📊 PERFORMANCE COMPARISON:\n");
        
        int[] sizes = {100, 1_000, 10_000, 100_000, 1_000_000};
        
        System.out.println("Array Size    | Manual Loop | System.arraycopy | Speedup");
        System.out.println("------------- | ----------- | ---------------- | -------");
        
        for (int size : sizes) {
            int[] source = new int[size];
            int[] dest1 = new int[size];
            int[] dest2 = new int[size];
            
            // Warm up
            for (int i = 0; i < 10; i++) {
                System.arraycopy(source, 0, dest1, 0, size);
                manualArrayCopy(source, 0, dest2, 0, size);
            }
            
            // Test manual copy
            long start1 = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                manualArrayCopy(source, 0, dest1, 0, size);
            }
            long time1 = System.nanoTime() - start1;
            
            // Test System.arraycopy
            long start2 = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                System.arraycopy(source, 0, dest2, 0, size);
            }
            long time2 = System.nanoTime() - start2;
            
            double speedup = (double) time1 / time2;
            
            System.out.printf("%-13s | %8.3f ms | %12.3f ms | %.2fx\n",
                String.format("%,d", size),
                time1 / 1_000_000.0,
                time2 / 1_000_000.0,
                speedup);
        }
        
        System.out.println("\n💡 Note: Speedup increases with array size!");
        System.out.println("   Larger arrays → better SIMD utilization → bigger speedup");
    }
}



