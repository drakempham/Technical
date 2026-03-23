public class HashMapImpl {
  private static final int BUCKET_SIZE = 1000;

  private static class Node {
    int key;
    int value;
    Node next;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  private final Node[] buckets;

  public HashMapImpl() {
    buckets = new Node[BUCKET_SIZE];
  }

  public void put(int key, int value) {
    int pos = key % BUCKET_SIZE;
    if (buckets[pos] == null) {
      buckets[pos] = new Node(key, value);
      return;
    }

    Node current = buckets[pos];
    while (current != null) {
      if (current.key == key) {
        current.value = value;
        return;
      }

      if (current.next == null) {
        current.next = new Node(key, value);
      }
      current = current.next;
    }
  }

  public int get(int key) {
    int pos = key % BUCKET_SIZE;
    if (buckets[pos] == null) {
      return -1;
    }

    Node current = buckets[pos];
    while (current != null) {
      if (current.key == key) {
        return current.value;
      }

      current = current.next;
    }

    return -1;
  }

  // remove in linkedList must go from previous element, always check head first
  public void remove(int key) {
    int pos = key % BUCKET_SIZE;
    // not find element
    if (buckets[pos] == null) {
      return;
    }

    if (buckets[pos].key == key) {
      buckets[pos] = buckets[pos].next;
      return;
    }

    Node current = buckets[pos];
    while (current.next != null) {
      if (current.next.key == key) {
        current.next = current.next.next;
        return;
      }

      current = current.next;
    }
  }
}
