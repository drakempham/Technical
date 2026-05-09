package day092026;

import java.util.Stack;

public class MinStack {
  Stack<Integer> data = new Stack<>();
  Stack<Integer> min = new Stack<>();

  public MinStack() {

  }

  public void push(int val) {
    data.push(val);
    if (min.isEmpty() || val <= min.peek()) {
      min.push(val);
    }
  }

  public void pop() {
    int val = data.pop();
    if (val == min.peek()) {
      min.pop();
    }
  }

  public int top() {
    return data.peek();
  }

  public int getMin() {
    return min.peek();
  }

  public static void main(String[] args) {
    MinStack sol = new MinStack();
    sol.push(-2);
    sol.push(0);
    sol.push(-3);
  }
}
