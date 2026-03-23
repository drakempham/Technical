import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ReorderList {
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  // Using List
  public void reorderListArrayList(ListNode head) {
    if (head.next == null) {
      return;
    }

    List<ListNode> nodes = new ArrayList<>();
    ListNode current = head;
    while (current != null) {
      nodes.add(current);
      current = current.next;
    }

    int left = 0, right = nodes.size() - 1;
    // break left == right for case even length (stop at right)
    while (left < right) {
      nodes.get(left).next = nodes.get(right);
      left++;
      // prevent circular preference
      if (left == right) {
        break;
      }
      nodes.get(right).next = nodes.get(left);
      right--;
    }

    nodes.get(left).next = null; // terminate the list
  }

  // Using deque
  public void reorderListDeque(ListNode head) {
    Deque<ListNode> deque = new ArrayDeque<>();
    ListNode current = head;
    while (current != null) {
      deque.add(current);
      current = current.next;
    }

    ListNode dummy = new ListNode(0);
    ListNode newHead = dummy;

    while (!deque.isEmpty()) {
      newHead.next = deque.pollFirst();
      newHead = newHead.next;

      if (!deque.isEmpty()) {
        newHead.next = deque.pollLast();
        newHead = newHead.next;
      }
    }

    newHead.next = null;
    head = dummy.next;
  }

  // using three-step maniulation
  // the idea is link between first half and reverse second half
  public void reorderList(ListNode head) {
    ListNode slow = head, fast = head.next;

    // split array, now slow is the last element of first head
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // reverse linkedList
    ListNode nextNode = slow.next;
    ListNode prevNode = null;
    slow.next = null; // cut first half

    // quy tac nam vai
    // nguoi sau nam vai nguoi truc, nguoi truoc the cho la nguoi sau, nguoi sau la
    // nguoi tiep theo -> can temp
    while (nextNode != null) {
      ListNode tmp = nextNode.next;
      nextNode.next = prevNode;
      prevNode = nextNode;
      nextNode = tmp;
    }

    // merge/ Interleave
    ListNode first = head, second = prevNode;
    while (first != null && second != null) {
      ListNode firstNext = first.next, secondNext = second.next;
      first.next = second;
      second.next = firstNext;
      first = firstNext;
      second = secondNext;
    }
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ReorderList solution = new ReorderList();
    solution.reorderList(head);

    ListNode current = head;
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }

    // even list
    ListNode head2 = new ListNode(1);
    head2.next = new ListNode(2);
    head2.next.next = new ListNode(3);
    head2.next.next.next = new ListNode(4);
    solution.reorderList(head2);
    current = head2;
    System.out.println();
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }
  }
}
