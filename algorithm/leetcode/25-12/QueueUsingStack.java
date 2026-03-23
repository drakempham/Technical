import java.util.Stack;

// Rule Implementation queue and stack: queue push top, stack push middle
public class QueueUsingStack {

  Stack<Integer> stack1;
  Stack<Integer> stack2;

  public QueueUsingStack() {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }

  // in -> 3 2 1 -> out
  public void push(int x) {

    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }

    stack2.push(x); // 1 2 3 4 -> out

    while (!stack2.isEmpty()) {
      stack1.push(stack2.pop()); // 4 3 2 1
    }
  }

  public int pop() {
    if (stack1.isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    return stack1.pop();
  }

  public int peek() {
    if (stack1.isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    return stack1.peek();
  }

  public boolean empty() {
    return stack1.isEmpty();
  }

  public static void main(String[] args) {
    QueueUsingStack queue = new QueueUsingStack();
    queue.push(1);
    queue.push(2);
    queue.push(3);
    System.out.println(queue.peek()); // 1
    System.out.println(queue.pop()); // 1
    System.out.println(queue.peek()); // 2
    System.out.println(queue.empty()); // false
  }
}
