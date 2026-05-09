package day0302026;

public class SwapNodeInPairs {
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    dummy.next = head;

    while (current.next != null && current.next.next != null) {
      ListNode first = current.next;
      ListNode second = current.next.next;

      first.next = second.next;
      second.next = first;

      current.next = second;
      current = current.next.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    SwapNodeInPairs solution = new SwapNodeInPairs();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);

    ListNode result = solution.swapPairs(head);
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }
}
