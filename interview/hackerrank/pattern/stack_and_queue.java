package interview.hackerrank.pattern;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stack_and_queue {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < 10; i++) {
      stack.add(i);
    }

    for (int i = 0; i < stack.size(); i++) {
      int element = stack.remove(0);
      queue.add(element);
      i -= 1;
    }

    for (int i = 0; i < queue.size(); i++) {
      int n = queue.remove();
      System.out.println("Element " + n);
      i -= 1;

    }
    while (!stack.isEmpty()) {
      System.out.println("Stack " + stack.pop());
    }
  }

}
