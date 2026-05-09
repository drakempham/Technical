package common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        System.out.println("Node 1 value: " + node1.val); // Output: Node 1 value: 1
        System.out.println("Node 2 value: " + node1.next.val); // Output: Node 2 value: 2
    }
}
