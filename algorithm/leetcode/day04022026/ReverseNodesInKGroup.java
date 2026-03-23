package day04022026;

public class ReverseNodesInKGroup {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }

  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1)
      return head;
    ListNode curr = head;
    for (int i = 0; i < k; i++) {
      if (curr == null) {
        return head;
      }
      curr = curr.next; // curr now is 3
    }

    // reverse first then link with new node
    // reversehead in new head of the reverse list
    ListNode reverseHead = reverse(head, k);

    head.next = reverseKGroup(curr, k);

    return reverseHead;
  }

  public ListNode reverse(ListNode head, int k) {
    ListNode prev = null;
    ListNode curr = head;

    for (int i = 0; i < k; i++) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  public static void main(String[] args) {
    ReverseNodesInKGroup solution = new ReverseNodesInKGroup();
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    // head.next.next = new ListNode(3);
    // head.next.next.next = new ListNode(4);
    // head.next.next.next.next = new ListNode(5);
    int k = 2;
    ListNode result = solution.reverseKGroup(head, k);
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }
}