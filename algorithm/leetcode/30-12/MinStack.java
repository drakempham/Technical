import java.util.Stack;

public class MinStack {
  Stack<Integer> st;
  Stack<Integer> minSt;

  public MinStack() {
    st = new Stack<>();
    minSt = new Stack<>();
  }

  public void push(int val) {
    if (minSt.isEmpty() || val <= minSt.peek()) {
      minSt.push(val);
    }

    st.push(val);
  }

  public void pop() {
    if (!st.isEmpty()) {
      int val = st.pop();
      if (val == minSt.peek()) {
        minSt.pop();
      }
    }
  }

  public int top() {
    return st.isEmpty() ? -1 : st.peek();
  }

  // Returns the minimum element in the stack.
  // the nature is that the minSt keep first element ... minimum element 1 ...
  // minimum element 2 ...
  public int getMin() {
    return minSt.isEmpty() ? -1 : minSt.peek();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin()); // Returns -3
    minStack.pop();
    System.out.println(minStack.top()); // Returns 0
    System.out.println(minStack.getMin()); // Returns -2
  }
}
