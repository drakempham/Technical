import java.util.HashMap;

public class CopyRandomList {
  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = next;
      this.random = random;
    }
  }

  // We need traverse the original list two times
  // First time: create a copy of new node and linked them with the old nodes
  // Second time: link new node together
  public Node copyRandomListHashMap(Node head) {
    if (head == null) {
      return null;
    }

    HashMap<Node, Node> newNodeMapping = new HashMap<>();
    Node current = head;
    while (current != null) {
      newNodeMapping.put(current, new Node(current.val)); // create new node linked with old one corresponding
      current = current.next;
    }

    current = head;
    while (current != null) {
      Node newNode = newNodeMapping.get(current);

      newNode.next = newNodeMapping.get(current.next);

      newNode.random = newNodeMapping.get(current.random);

      current = current.next;
    }

    return newNodeMapping.get(head);
  }

  // interleave ( we have a and b)
  // using interweave ( đan xen we only have a, try to create b in between a)
  // We create new nodes and insert them right after the original nodes:
  // A->A'->B->B'->C->C'
  // and then split them into two lists
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }

    // create new Node
    Node current = head;
    while (current != null) {
      Node newNode = new Node(current.val);
      newNode.next = current.next;
      current.next = newNode;
      current = current.next.next; // skip the new node
    }

    current = head; // detach random
    while (current != null) {
      if (current.random != null) {
        current.next.random = current.random.next; // A.random = C -> A'.random = C' (C' and A' is next of C and A)
      }
      current = current.next.next;
    }

    // split two nodes;
    Node dummy = new Node(0);
    Node newNode = dummy;
    current = head;
    while (current != null) {
      newNode.next = current.next;
      newNode = newNode.next;
      current.next = current.next.next;
      current = current.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    CopyRandomList solution = new CopyRandomList();
    Node head = new Node(7);
    head.next = new Node(13);
    head.next.next = new Node(11);
    head.next.next.next = new Node(10);
    head.next.next.next.next = new Node(1);

    head.random = null;
    head.next.random = head;
    head.next.next.random = head.next.next.next.next;
    head.next.next.next.random = head.next.next;
    head.next.next.next.next.random = head;

    Node result = solution.copyRandomList(head);

    while (result != null) {
      System.out.print("Node val: " + result.val);
      if (result.random != null) {
        System.out.print(", Random val: " + result.random.val);
      } else {
        System.out.print(", Random val: null");
      }
      System.out.println();
      result = result.next;
    }
  }
  // expected output:
  // Node val: 7, Random val: null
  // Node val: 13, Random val: 7
  // Node val: 11, Random val: 1
  // Node val: 10, Random val: 11
  // Node val: 1, Random val: 7

}
