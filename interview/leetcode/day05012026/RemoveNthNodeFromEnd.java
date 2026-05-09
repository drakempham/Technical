public class RemoveNthNodeFromEnd {
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode removeNthNodeFromEnd(ListNode head, int n) {
    if (head.next == null) {
      return null;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode slow = dummy, fast = dummy;

    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
  }

  public static void main(String[] args) {
    RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();
    // ListNode head = new ListNode(1);
    // head.next = new ListNode(2);
    // head.next.next = new ListNode(3);
    // head.next.next.next = new ListNode(4);
    // head.next.next.next.next = new ListNode(5);

    // int n = 2;
    // ListNode result = solution.removeNthNodeFromEnd(head, n);

    // while (result != null) {
    // System.out.print(result.val + " ");
    // result = result.next;
    // }

    // [1,2] , n = 2
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    int n = 2;
    ListNode result = solution.removeNthNodeFromEnd(head, n);
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }

  }

}
