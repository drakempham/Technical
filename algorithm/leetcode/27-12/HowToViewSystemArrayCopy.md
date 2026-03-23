# Cách Xem Source Code của System.arraycopy()

## 📋 Tổng Quan

`System.arraycopy()` là một **native method** trong Java, nghĩa là:
- **Declaration** (khai báo) ở Java: `java.lang.System.java`
- **Implementation** (triển khai) ở C/C++: trong JVM source code

## 🔍 Các Cách Xem Source Code

### 1. Xem Java Declaration (Trong JDK Source)

**File:** `java/lang/System.java`

```java
public static native void arraycopy(Object src, int srcPos,
                                     Object dest, int destPos,
                                     int length);
```

**Cách xem:**
- Download OpenJDK source: https://github.com/openjdk/jdk
- Hoặc xem online: https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/System.java

### 2. Xem Native Implementation (C/C++)

**Trong OpenJDK, implementation nằm ở:**

#### a) Main Implementation:
- `hotspot/src/share/vm/runtime/arraycopy.cpp`
- `hotspot/src/share/vm/oops/objArrayKlass.cpp`

#### b) Platform-specific (x86_64):
- `hotspot/src/cpu/x86/vm/stubGenerator_x86_64.cpp`

**Cách xem:**
1. Clone OpenJDK repository:
   ```bash
   git clone https://github.com/openjdk/jdk.git
   cd jdk
   ```

2. Xem file C++:
   ```bash
   # Main implementation
   cat hotspot/src/share/vm/runtime/arraycopy.cpp
   
   # x86_64 optimized version
   cat hotspot/src/cpu/x86/vm/stubGenerator_x86_64.cpp
   ```

### 3. Xem Online (Không cần download)

**Java Declaration:**
- https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/System.java
- Tìm method `arraycopy` trong file này

**C++ Implementation:**
- https://github.com/openjdk/jdk/blob/master/src/hotspot/share/runtime/arraycopy.cpp
- https://github.com/openjdk/jdk/blob/master/src/hotspot/share/oops/objArrayKlass.cpp

### 4. Sử Dụng IDE (IntelliJ IDEA, Eclipse)

1. **IntelliJ IDEA:**
   - Ctrl+Click (Cmd+Click trên Mac) vào `System.arraycopy`
   - IDE sẽ hiển thị Java declaration
   - Để xem native code, cần attach JDK source

2. **Eclipse:**
   - F3 hoặc Ctrl+Click vào method
   - Xem "Declaration" tab

### 5. Sử Dụng Reflection (Như trong ViewSystemArrayCopy.java)

```java
Method method = System.class.getDeclaredMethod(
    "arraycopy", 
    Object.class, int.class, Object.class, int.class, int.class
);
System.out.println("Is Native: " + 
    Modifier.isNative(method.getModifiers()));
```

## 📝 Ví Dụ Implementation Logic (Pseudo-code)

Dựa trên OpenJDK source, logic tương tự như sau:

```cpp
// Simplified version (không phải code thật)
void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
    // 1. Null checks
    if (src == null || dest == null) {
        throw NullPointerException();
    }
    
    // 2. Bounds checking
    if (srcPos < 0 || destPos < 0 || length < 0 ||
        srcPos + length > src.length ||
        destPos + length > dest.length) {
        throw ArrayIndexOutOfBoundsException();
    }
    
    // 3. Type checking (nếu là Object arrays)
    if (src và dest không compatible) {
        throw ArrayStoreException();
    }
    
    // 4. Optimized memory copy
    if (primitive arrays) {
        // Dùng memcpy hoặc SIMD instructions
        memcpy(dest + destPos, src + srcPos, length * sizeof(type));
    } else {
        // Object arrays: copy references + write barriers
        Copy::conjoint_oops_atomic(src, dest, length);
    }
}
```

## 🚀 Tại Sao System.arraycopy() Nhanh Hơn?

1. **Native Code (C/C++):** Không có overhead của JVM
2. **SIMD Instructions:** Có thể dùng vectorized operations
3. **Memory Alignment:** Xử lý memory alignment tốt hơn
4. **Bulk Copy:** Copy theo khối lớn thay vì từng phần tử
5. **JIT Optimization:** JVM có thể inline và optimize tốt hơn

## 🔗 Links Hữu Ích

- **OpenJDK Repository:** https://github.com/openjdk/jdk
- **System.java:** https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/System.java
- **arraycopy.cpp:** https://github.com/openjdk/jdk/blob/master/src/hotspot/share/runtime/arraycopy.cpp
- **Stack Overflow Discussion:** https://stackoverflow.com/questions/11210369/openjdk-implementation-of-system-arraycopy

## 💡 Lưu Ý

- Native code phức tạp và phụ thuộc vào platform
- Implementation khác nhau giữa các JVM (HotSpot, OpenJ9, etc.)
- Để hiểu sâu cần kiến thức về C/C++ và JVM internals



