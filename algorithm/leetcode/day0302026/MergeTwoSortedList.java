package day0302026;

public class MergeTwoSortedList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }
  
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1!=null && l2!= null) {
      if (l1.val < l2.val) {
        current.next = new ListNode(l1.val);
        l1 = l1.next;
      } else {
        current.next = new ListNode(l2.val);
        l2 = l2.next;
      }

      current = current.next;
    }

    while (l1 != null) {
      current.next = new ListNode(l1.val);
      l1 = l1.next;
      current = current.next;
    }

    while (l2 != null) {
      current.next = new ListNode(l2.val);
      l2 = l2.next;
      current = current.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    MergeTwoSortedList solution = new MergeTwoSortedList();
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);
    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);
    ListNode result = solution.mergeTwoLists(l1, l2);
    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }
}