import java.util.LinkedList;
import java.util.Queue;

// using two queue so it's mean 
public class StackUsingQueue {

  Queue<Integer> queue1;
  Queue<Integer> queue2;

  public StackUsingQueue() {
    queue1 = new LinkedList<>();
    queue2 = new LinkedList<>();
  }

  public void push(int x) {
    // always keep the last element on the top
    queue2.offer(x);

    while (!queue1.isEmpty()) {
      queue2.offer(queue1.poll());
    }

    // swap queue1 and queue2 -> queue2 now is empty
    Queue<Integer> temp = queue1;
    queue1 = queue2;
    queue2 = temp;
  }

  public int pop() {
    if (queue1.isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    return queue1.poll();
  }

  public int top() {
    if (queue1.isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    return queue1.peek();
  }

  public boolean isEmpty() {
    return queue1.isEmpty();
  }

  public static void main(String[] args) {
    StackUsingQueue stack = new StackUsingQueue();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    System.out.println(stack.top()); // 3
    System.out.println(stack.pop()); // 3
    System.out.println(stack.top()); // 2
    System.out.println(stack.isEmpty()); // false
  }
}
