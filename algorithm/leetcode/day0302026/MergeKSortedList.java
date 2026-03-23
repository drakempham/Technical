package day0302026;

import java.util.PriorityQueue;

public class MergeKSortedList {
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }
    
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
    for (ListNode list : lists) {
      minHeap.add(list);
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (!minHeap.isEmpty()) {
      ListNode minNode = minHeap.poll();
      current.next = minNode;
      current = current.next;
      if (minNode.next != null) {
        minHeap.add(minNode.next);
      }
    }

    return dummy.next;
  }
    
  // optimize solution
  public ListNode mergeKListsOptimize(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    return mergeKListsHelper(lists, 0, lists.length - 1);
  }
  
  public ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
    if (start == end) {
      return lists[start];
    }

    int mid = start + (end - start) / 2;
    ListNode left = mergeKListsHelper(lists, start, mid);
    ListNode right = mergeKListsHelper(lists, mid + 1, end);

    return merge(left, right);
  }

  public ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }

    if (l1 != null) {
      current.next = l1;
    } else {
      current.next = l2;
    }

    return dummy.next;
  }

    public static void main(String[] args) {
      MergeKSortedList solution = new MergeKSortedList();
      ListNode[] lists = new ListNode[3];
      lists[0] = new ListNode(1);
      lists[0].next = new ListNode(4);
      lists[0].next.next = new ListNode(5);
      
      lists[1] = new ListNode(1);
      lists[1].next = new ListNode(3);
      lists[1].next.next = new ListNode(4);

      lists[2] = new ListNode(2);
      lists[2].next = new ListNode(6);

      ListNode result = solution.mergeKLists(lists);
      while (result != null) {
        System.out.print(result.val + " ");
        result = result.next;
      }
    }
}