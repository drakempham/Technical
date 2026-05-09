import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

/**
 * MinStack Implementation
 * 
 * Requirements:
 * - push(val): O(1)
 * - pop(): O(1)
 * - top(): O(1)
 * - getMin(): O(1)
 */
public class MinStack {
    
    /**
     * ============================================
     * APPROACH 1: BRUTE FORCE
     * ============================================
     * 
     * Time Complexity:
     * - push(): O(1)
     * - pop(): O(1)
     * - top(): O(1)
     * - getMin(): O(n) ❌ (scans entire stack)
     * 
     * Space Complexity: O(n)
     */
    static class MinStackBruteForce {
        private Stack<Integer> stack;
        
        public MinStackBruteForce() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            stack.pop();
        }
        
        public int top() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.peek();
        }
        
        /**
         * BRUTE FORCE: Scan entire stack to find minimum
         * Time: O(n) - not optimal!
         */
        public int getMin() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            
            // Create a temporary stack to preserve original stack
            Stack<Integer> tempStack = new Stack<>();
            int min = Integer.MAX_VALUE;
            
            // Pop all elements, find minimum
            while (!stack.isEmpty()) {
                int val = stack.pop();
                min = Math.min(min, val);
                tempStack.push(val);
            }
            
            // Restore original stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
            
            return min;
        }
    }
    
    /**
     * ============================================
     * APPROACH 2: OPTIMIZED - Two Stacks
     * ============================================
     * 
     * Use an auxiliary stack to track minimums
     * 
     * Time Complexity: O(1) for all operations ✅
     * Space Complexity: O(n) - worst case same elements
     */
    static class MinStackOptimized {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;  // Tracks minimum at each level
        
        public MinStackOptimized() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            stack.push(val);
            // Push to minStack if it's empty or val <= current minimum
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            
            int popped = stack.pop();
            // If we're popping the current minimum, remove it from minStack
            if (popped == minStack.peek()) {
                minStack.pop();
            }
        }
        
        public int top() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.peek();
        }
        
        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return minStack.peek();
        }
    }
    
    /**
     * ============================================
     * APPROACH 3: OPTIMIZED - Single Stack with Pairs
     * ============================================
     * 
     * Store (value, minSoFar) pairs in a single stack
     * 
     * Time Complexity: O(1) for all operations ✅
     * Space Complexity: O(n)
     */
    static class MinStackSingleStack {
        private Stack<int[]> stack;  // [value, minSoFar]
        
        public MinStackSingleStack() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val});
            } else {
                int currentMin = stack.peek()[1];
                stack.push(new int[]{val, Math.min(val, currentMin)});
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            stack.pop();
        }
        
        public int top() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.peek()[0];
        }
        
        public int getMin() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.peek()[1];
        }
    }
    
    /**
     * ============================================
     * APPROACH 4: OPTIMIZED - Using ArrayList (Alternative)
     * ============================================
     * 
     * Similar to two stacks but using ArrayList
     */
    static class MinStackArrayList {
        private ArrayList<Integer> stack;
        private ArrayList<Integer> minStack;
        
        public MinStackArrayList() {
            stack = new ArrayList<>();
            minStack = new ArrayList<>();
        }
        
        public void push(int val) {
            stack.add(val);
            if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1)) {
                minStack.add(val);
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            int popped = stack.remove(stack.size() - 1);
            if (!minStack.isEmpty() && popped == minStack.get(minStack.size() - 1)) {
                minStack.remove(minStack.size() - 1);
            }
        }
        
        public int top() {
            if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return stack.get(stack.size() - 1);
        }
        
        public int getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }
            return minStack.get(minStack.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== BRUTE FORCE APPROACH ===");
        MinStackBruteForce bruteForce = new MinStackBruteForce();
        bruteForce.push(-2);
        bruteForce.push(0);
        bruteForce.push(-3);
        System.out.println("getMin(): " + bruteForce.getMin()); // -3
        bruteForce.pop();
        System.out.println("top(): " + bruteForce.top());       // 0
        System.out.println("getMin(): " + bruteForce.getMin()); // -2
        
        System.out.println("\n=== OPTIMIZED APPROACH (Two Stacks) ===");
        MinStackOptimized optimized = new MinStackOptimized();
        optimized.push(-2);
        optimized.push(0);
        optimized.push(-3);
        System.out.println("getMin(): " + optimized.getMin()); // -3
        optimized.pop();
        System.out.println("top(): " + optimized.top());       // 0
        System.out.println("getMin(): " + optimized.getMin()); // -2
        
        System.out.println("\n=== OPTIMIZED APPROACH (Single Stack) ===");
        MinStackSingleStack singleStack = new MinStackSingleStack();
        singleStack.push(-2);
        singleStack.push(0);
        singleStack.push(-3);
        System.out.println("getMin(): " + singleStack.getMin()); // -3
        singleStack.pop();
        System.out.println("top(): " + singleStack.top());       // 0
        System.out.println("getMin(): " + singleStack.getMin()); // -2
        
        System.out.println("\n=== Test Case: Duplicate Minimums ===");
        MinStackOptimized test = new MinStackOptimized();
        test.push(5);
        test.push(2);
        test.push(2);  // Duplicate minimum
        test.push(3);
        System.out.println("getMin(): " + test.getMin()); // 2
        test.pop();
        System.out.println("getMin(): " + test.getMin()); // 2 (still 2)
        test.pop();
        System.out.println("getMin(): " + test.getMin()); // 2 (still 2)
        test.pop();
        System.out.println("getMin(): " + test.getMin()); // 5
    }
}




