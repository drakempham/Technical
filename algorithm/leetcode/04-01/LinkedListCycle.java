public class LinkedListCycle {

  // ListNode definition
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (fast != null && fast.next != null) {
      if (slow == fast) {
        return true;
      }

      slow = slow.next;
      fast = fast.next.next;
    }

    return false;
  }

  public static void main(String[] args) {
    // Create a cycle: 3 -> 2 -> 0 -> -4 -> back to 2
    ListNode head = new ListNode(3);
    head.next = new ListNode(2);
    head.next.next = new ListNode(0);
    head.next.next.next = new ListNode(-4);
    head.next.next.next.next = head.next; // cycle

    LinkedListCycle solution = new LinkedListCycle();
    System.out.println("Has cycle: " + solution.hasCycle(head)); // true
  }
}
