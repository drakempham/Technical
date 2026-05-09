import java.lang.reflect.Method;

/**
 * Cách xem source code của System.arraycopy()
 * 
 * System.arraycopy() là một native method, được implement bằng C/C++ trong JVM
 */
public class ViewSystemArrayCopy {
    
    public static void main(String[] args) {
        // CÁCH 1: Xem Java declaration (sẽ thấy từ khóa "native")
        System.out.println("=== CÁCH 1: Xem Java Declaration ===");
        try {
            Method arraycopyMethod = System.class.getDeclaredMethod(
                "arraycopy", 
                Object.class, int.class, Object.class, int.class, int.class
            );
            
            System.out.println("Method: " + arraycopyMethod);
            System.out.println("Is Native: " + java.lang.reflect.Modifier.isNative(arraycopyMethod.getModifiers()));
            System.out.println("Modifiers: " + java.lang.reflect.Modifier.toString(arraycopyMethod.getModifiers()));
            
            // Native methods không có body trong Java, chỉ có declaration
            System.out.println("\n⚠️  Đây là native method - implementation nằm trong JVM (C/C++)");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== CÁCH 2: Xem trong JDK Source ===");
        System.out.println("File: java/lang/System.java");
        System.out.println("Sẽ thấy: public static native void arraycopy(...)");
        
        System.out.println("\n=== CÁCH 3: Xem Native Implementation (C/C++) ===");
        System.out.println("Trong OpenJDK, implementation nằm ở:");
        System.out.println("  - hotspot/src/share/vm/runtime/arraycopy.cpp");
        System.out.println("  - hotspot/src/share/vm/oops/objArrayKlass.cpp");
        System.out.println("  - hotspot/src/cpu/x86/vm/stubGenerator_x86_64.cpp (cho x86_64)");
        
        System.out.println("\n=== CÁCH 4: Tìm trong JDK Installation ===");
        String javaHome = System.getProperty("java.home");
        System.out.println("Java Home: " + javaHome);
        System.out.println("Source code thường ở: " + javaHome + "/../src.zip");
        System.out.println("Hoặc download OpenJDK source từ: https://github.com/openjdk/jdk");
        
        System.out.println("\n=== Demo: So sánh performance ===");
        comparePerformance();
    }
    
    /**
     * So sánh performance giữa manual copy và System.arraycopy
     */
    public static void comparePerformance() {
        int size = 10_000_000;
        int[] source = new int[size];
        int[] dest1 = new int[size];
        int[] dest2 = new int[size];
        
        // Initialize source array
        for (int i = 0; i < size; i++) {
            source[i] = i;
        }
        
        // Test 1: Manual copy
        long start1 = System.nanoTime();
        for (int i = 0; i < size; i++) {
            dest1[i] = source[i];
        }
        long time1 = System.nanoTime() - start1;
        
        // Test 2: System.arraycopy (native, optimized)
        long start2 = System.nanoTime();
        System.arraycopy(source, 0, dest2, 0, size);
        long time2 = System.nanoTime() - start2;
        
        System.out.println("\nPerformance Comparison (size: " + size + "):");
        System.out.println("Manual loop:     " + time1 / 1_000_000.0 + " ms");
        System.out.println("System.arraycopy: " + time2 / 1_000_000.0 + " ms");
        System.out.println("Speedup: " + String.format("%.2f", (double)time1 / time2) + "x");
        System.out.println("\n💡 System.arraycopy thường nhanh hơn vì:");
        System.out.println("   - Được optimize ở native code (C/C++)");
        System.out.println("   - Có thể dùng SIMD instructions");
        System.out.println("   - Xử lý memory alignment tốt hơn");
    }
}



