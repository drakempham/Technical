# Beginner Friendly ⛳️ || Two Hash Maps + Doubly Linked List 🔗 || System Design 🛠️

## Intuition

If an LRU (Least Recently Used) Cache is like a bookshelf where you throw away the book you haven't touched in the longest time, an **LFU (Least Frequently Used) Cache** is a bookshelf where you throw away the book you've read the **fewest number of times**. If multiple books are tied for the lowest read count, you then fall back to the LRU rule (throw away the one you haven't touched in the longest time among the tied ones).

To achieve `O(1)` time for all operations, one Hash Map isn't enough anymore because we need to group nodes by their frequency. We need **two Hash Maps** and our trusty Doubly Linked List!
1. `key_to_node` Map: Quickly finds any node by its key.
2. `freq_to_doublyLinkedList` Map: Maps a frequency number (like `1`, `2`, `3`) to a Doubly Linked List. Each list holds all the nodes that have exactly that frequency.

## Why This Works

When we access or update a node, we look it up in `key_to_node` in `O(1)`. Then we find its current frequency, remove it from that frequency's Doubly Linked List, increase its frequency by `1`, and attach it to the front of the next frequency's Doubly Linked List. 

If we need to evict a node because the cache is full, we look at our `min_freq` tracker. We grab the Doubly Linked List at `freq_to_doublyLinkedList[min_freq]` and instantly pop its tail. Because every new node is added to the front of the list, the tail is guaranteed to be the Least Recently Used node within that specific frequency group! All of this happens exactly in `O(1)` time.

## Approach

- **Step 1: Setup Node and List structures.** Create a `Node` class that also stores `freq` (default is 1). Create a `DoublyLinkedList` class with `add_to_front`, `remove`, and `remove_tail` methods using dummy head and tail nodes.
- **Step 2: Setup the LFU Cache structure.** Initialize `key_to_node`, `freq_to_list`, `capacity`, and a `min_freq` integer to keep track of the lowest frequency currently in the cache.
- **Step 3: The `update_freq` Helper.** When a node is accessed or updated:
  - Remove it from its old frequency list.
  - If its old frequency list becomes completely empty AND its old frequency was the `min_freq`, then bump up the `min_freq` by 1.
  - Add the node to the front of the `old_freq + 1` list.
- **Step 4: `get(key)` Operation.** Return `-1` if missing. If found, call `update_freq(node)` and return the value.
- **Step 5: `put(key, value)` Operation.** 
  - Handle `capacity == 0` edge case immediately.
  - If key exists: Update its value, call `update_freq(node)`, and return.
  - If key is new: **Evict first if full!** Look up the list at `min_freq`, remove its tail, and delete that tail's key from `key_to_node`. Then, create the new node, add it to `key_to_node`, add it to the front of the `freq = 1` list, and strictly set `min_freq = 1`.

## Complexity

- Time complexity: `O(1)` for both `get` and `put` operations. Dictionary lookups are `O(1)` and moving nodes between doubly linked lists takes `O(1)` time.
- Space complexity: `O(capacity)` since we store at most `capacity` items in our mappings and linked lists.

## Code

**Python:**
```python
class Node:
    def __init__(self, key=0, val=0, freq=1):
        self.key = key
        self.val = val
        self.freq = freq
        self.prev = None
        self.next = None

class DoublyLinkedList:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def add_to_front(self, node: Node):
        first_node = self.head.next
        node.prev = self.head
        node.next = first_node
        self.head.next = node
        first_node.prev = node
        
    def remove(self, node: Node):
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node

    def remove_tail(self):
        if self.tail.prev == self.head:
            return None
        node = self.tail.prev
        self.remove(node)
        return node

class LFUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.min_freq = 0
        self.key_to_node = {}
        self.freq_to_doublyLinkedList = {}

    def update_freq(self, node: Node):
        old_freq = node.freq
        old_list = self.freq_to_doublyLinkedList.get(old_freq)
        old_list.remove(node)

        # If the old list is now empty and it was the min_freq, increment min_freq
        if old_freq == self.min_freq and old_list.tail.prev == old_list.head:
            self.min_freq += 1
        
        new_freq = old_freq + 1
        node.freq = new_freq
        if new_freq not in self.freq_to_doublyLinkedList:
            self.freq_to_doublyLinkedList[new_freq] = DoublyLinkedList()

        self.freq_to_doublyLinkedList[new_freq].add_to_front(node)

    def get(self, key: int) -> int:
        if key not in self.key_to_node:
            return -1
        
        node = self.key_to_node[key]
        self.update_freq(node)
        return node.val
        
    def put(self, key: int, value: int) -> None:
        if self.capacity == 0:
            return

        if key in self.key_to_node:
            node = self.key_to_node[key]
            node.val = value
            self.update_freq(node)
            return
        
        # EVICT FIRST if at capacity before adding new node
        if len(self.key_to_node) == self.capacity:
            list_to_remove = self.freq_to_doublyLinkedList[self.min_freq]
            node_to_remove = list_to_remove.remove_tail()
            if node_to_remove:
                del self.key_to_node[node_to_remove.key]

        new_node = Node(key, value)
        self.key_to_node[key] = new_node

        if 1 not in self.freq_to_doublyLinkedList:
            self.freq_to_doublyLinkedList[1] = DoublyLinkedList()
        
        self.freq_to_doublyLinkedList[1].add_to_front(new_node)
        self.min_freq = 1
```

**Java:**
```java
class LFUCache {
    class Node {
        int key;
        int val;
        int freq;
        Node prev;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }
    
    class DoublyLinkedList {
        Node head;
        Node tail;
        public DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }
        
        public void addToFront(Node node) {
            Node firstNode = head.next;
            node.prev = head;
            node.next = firstNode;
            head.next = node;
            firstNode.prev = node;
        }
        
        public void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        
        public Node removeTail() {
            if (tail.prev == head) return null;
            Node node = tail.prev;
            remove(node);
            return node;
        }
    }

    int capacity;
    int minFreq;
    Map<Integer, Node> keyToNode;
    Map<Integer, DoublyLinkedList> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToNode = new HashMap<>();
        this.freqToList = new HashMap<>();
    }
    
    private void updateFreq(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqToList.get(oldFreq);
        oldList.remove(node);
        
        if (oldFreq == minFreq && oldList.head.next == oldList.tail) {
            minFreq++;
        }
        
        int newFreq = oldFreq + 1;
        node.freq = newFreq;
        freqToList.putIfAbsent(newFreq, new DoublyLinkedList());
        freqToList.get(newFreq).addToFront(node);
    }
    
    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        Node node = keyToNode.get(key);
        updateFreq(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            updateFreq(node);
            return;
        }
        
        // EVICT FIRST if at capacity
        if (keyToNode.size() == capacity) {
            DoublyLinkedList listToRemove = freqToList.get(minFreq);
            Node nodeToRemove = listToRemove.removeTail();
            if (nodeToRemove != null) {
                keyToNode.remove(nodeToRemove.key);
            }
        }
        
        Node newNode = new Node(key, value);
        keyToNode.put(key, newNode);
        freqToList.putIfAbsent(1, new DoublyLinkedList());
        freqToList.get(1).addToFront(newNode);
        minFreq = 1;
    }
}
```

## Interview Tips / Fast Recall

- **The Magic Formula**: LFU Cache = `HashMap<Key, Node>` + `HashMap<Freq, DoublyLinkedList>` + `min_freq` Tracker.
- **Evict BEFORE Adding**: The most common trap! If the cache is full, you absolutely MUST evict the LFU node **before** you add the new node to the map. Otherwise, your logic might falsely evict the shiny new node you just inserted.
- **Incrementing min_freq**: When you update a node's frequency (like jumping from `freq=1` to `freq=2`), check if its old frequency list becomes entirely empty. If it does, AND that old frequency was equal to `min_freq`, you must manually increment `min_freq += 1`.
- **Breaking Ties**: Since every new node or updated node is slapped onto the **front** of a Doubly Linked List, the **tail** of that list inherently becomes the Least Recently Used (LRU) node for that specific frequency tier.

## Similar problem

Some example problems with same pattern
- 146. LRU Cache 🧠
- 432. All O`one Data Structure 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
