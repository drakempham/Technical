
import java.util.Stack;

public class Reversedlinkedlist {
  class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  // O(n) space
  public ListNode reverseListStack(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    Stack<ListNode> stack = new Stack<>();
    while (head != null) {
      stack.push(head);
      head = head.next;
    }

    ListNode newHead = stack.pop();
    ListNode current = newHead;

    while (!stack.isEmpty()) {
      current.next = stack.pop();
      current = current.next;
    }

    current.next = null; // prevent infinite loop

    return newHead;
  }

  public static void main(String[] args) {
    Reversedlinkedlist solution = new Reversedlinkedlist();
    ListNode head = solution.new ListNode(1,
        solution.new ListNode(2, solution.new ListNode(3, solution.new ListNode(4, solution.new ListNode(5)))));
    ListNode reversedHead = solution.reverseList(head);

    // Print reversed list
    while (reversedHead != null) {
      System.out.print(reversedHead.val + " ");
      reversedHead = reversedHead.next;
    }
  }
}