public class HashSetImpl {
  private class Node {
    private Node next;
    private int value;

    public Node(int value) {
      this.next = null;
      this.value = value;
    }
  }

  // Design a hashSet
  // ele from 0 to 10^6 - we should load factor = total items/ total size ~ 75% ->
  // this make each bucket has about 0-> 2 items
  private static int BUCKET_SIZE = 10000;
  private Node[] buckets;

  public HashSetImpl() {
    this.buckets = new Node[BUCKET_SIZE];
  }

  public void add(int val) {
    int pos = val % BUCKET_SIZE;
    if (buckets[pos] == null) {
      buckets[pos] = new Node(val);
    }

    Node current = buckets[pos];
    while (current != null) {
      if (current.value == val) {
        return;
      }

      if (current.next == null) {
        current.next = new Node(val);
      }
      current = current.next;
    }

  }

  public void remove(int val) {
    int pos = val % BUCKET_SIZE;
    // not find element
    if (buckets[pos] == null) {
      return;
    }

    if (buckets[pos].value == val) {
      buckets[pos] = buckets[pos].next;
      return;
    }

    Node current = buckets[pos];
    while (current.next != null) {
      if (current.next.value == val) {
        current.next = current.next.next;
        return;
      }

      current = current.next;
    }
  }

  public boolean contains(int val) {
    int pos = val % BUCKET_SIZE;
    if (buckets[pos] == null) {
      return false;
    }

    Node current = buckets[pos];
    while (current != null) {
      if (current.value == val) {
        return true;
      }

      current = current.next;
    }

    return false;
  }

  public static void main(String[] args) {
    HashSetImpl set = new HashSetImpl();
    set.add(1);
    set.add(2);
    System.out.println(set.contains(1)); // true
    System.out.println(set.contains(3)); // false
    set.add(2);
    System.out.println(set.contains(2)); // true
    set.remove(2);
    System.out.println(set.contains(2)); // false
  }

}
