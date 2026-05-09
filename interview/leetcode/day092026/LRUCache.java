package day092026;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  static class Node {
    Node prev, next;
    int key, value;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  private Map<Integer, Node> map = new HashMap<>();
  private Node head, tail;
  private int capacity;

  public LRUCache(int capacity) {
    // init dummy nodes
    head = new Node(0, 0);
    tail = new Node(0, 0);
    head.next = tail;
    tail.prev = head;
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }

    Node node = map.get(key);
    remove(node);
    insertAtHead(node);

    return node.value;
  }

  public void put(int key, int value) {
    if (!map.containsKey(key) && map.size() == capacity) {
      map.remove(tail.prev.key);
      remove(tail.prev);
    }

    if (map.containsKey(key)) {
      remove(map.get(key));
    }

    Node newNode = new Node(key, value);
    map.put(key, newNode);
    insertAtHead(newNode);
  }

  public void remove(Node node) {
    node.next.prev = node.prev;
    node.prev.next = node.next;
  }

  public void insertAtHead(Node node) {
    node.next = head.next;
    node.next.prev = node;
    head.next = node;
    node.prev = head;
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1)); // 1
    cache.put(3, 3);
    System.out.println(cache.get(2)); // -1
    cache.put(4, 4);
    System.out.println(cache.get(1)); // -1
    System.out.println(cache.get(3)); // 3
    System.out.println(cache.get(4)); // 4
  }

}