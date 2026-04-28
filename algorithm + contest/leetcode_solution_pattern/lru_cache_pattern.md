# Beginner Friendly ⛳️ || Hash Map + Doubly Linked List 🔗 || System Design 🛠️

## Intuition

An LRU (Least Recently Used) Cache is like a bookshelf that only holds a certain number of books. When you need to put a new book on a full shelf, you have to throw away the book you haven't read in the longest time.

To make this extremely fast (`O(1)` time), we need two things:
1. **Hash Map (Dictionary)**: So we can find any book instantly without scanning the entire shelf.
2. **Doubly Linked List**: So we can instantly pull a book out from the middle and move it to the "recently read" (front) end without shifting everything else. 

By combining them, the Hash Map points directly to the Nodes inside the Linked List!

## Why This Works

A standard array or list requires `O(N)` time to shift elements when we move a recently used item to the front. A Doubly Linked List solves this because removing a node and inserting it at the front only requires changing a few pointers (`prev` and `next`), which takes exactly `O(1)` time. 

However, searching a Linked List takes `O(N)`. We fix this by storing the actual `Node` objects inside a Hash Map. Now, we can find the node in `O(1)`, and shift it in `O(1)`.

## Approach

- **Step 1: Setup the Node and Cache.** Create a `Node` class with `key`, `val`, `prev`, and `next`. Initialize a `cache` hash map and dummy `head` and `tail` nodes. Connect `head` to `tail`.
- **Step 2: Helper Functions.** 
  - `remove(node)`: Unlinks a node from its current neighbors.
  - `add_front(node)`: Inserts a node right after the dummy `head` (marking it as most recently used).
- **Step 3: `get(key)` Operation.** If the key exists, fetch the node from the hash map. Move it to the most recently used position by calling `remove(node)` then `add_front(node)`. Return the value.
- **Step 4: `put(key, value)` Operation.** 
  - If the key is new: Create a node, add it to the hash map, and `add_front(node)`.
  - If the key exists: Fetch the node, **update its value**, `remove(node)`, and `add_front(node)`.
- **Step 5: Eviction.** If `len(cache) > capacity`, grab the LRU node (`tail.prev`), `remove` it from the list, and delete its key from the hash map.

## Complexity

- Time complexity: `O(1)` for both `get` and `put` operations. Dictionary lookups are `O(1)` and moving nodes in a doubly linked list by reference is `O(1)`.
- Space complexity: `O(capacity)` since we store at most `capacity` items in both our hash map and the doubly linked list.

## Code

**Python:**
```python
class Node:
    def __init__(self, key=0, value=0):
        self.key = key
        self.val = value
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}

        self.head = Node()
        self.tail = Node()

        self.head.next = self.tail
        self.tail.prev = self.head
  
    def add_front(self, node: Node):
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
  
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1

        node = self.cache[key]
        
        self.remove(node)
        self.add_front(node)

        return node.val
  
    def put(self, key: int, value: int):
        if key not in self.cache:
            node = Node(key, value)
            self.cache[key] = node
            self.add_front(node)
        else:
            node = self.cache[key]
            node.val = value  # IMPORTANT: Update value
            self.remove(node)
            self.add_front(node)
        
        if len(self.cache) > self.capacity:
            last_node = self.tail.prev
            self.remove(last_node)
            del self.cache[last_node.key]
```

**Java:**
```java
class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    int capacity;
    Map<Integer, Node> cache = new HashMap<>();
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    private void addFront(Node node) {
        Node firstNode = head.next;
        node.prev = head;
        node.next = firstNode;
        head.next = node;
        firstNode.prev = node;
    }
    
    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        remove(node);
        addFront(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            Node node = new Node(key, value);
            cache.put(key, node);
            addFront(node);
        } else {
            Node node = cache.get(key);
            node.val = value; // IMPORTANT: Update value
            remove(node);
            addFront(node);
        }
        
        if (cache.size() > capacity) {
            Node lastNode = tail.prev;
            remove(lastNode);
            cache.remove(lastNode.key);
        }
    }
}
```

## Interview Tips / Fast Recall

- **The Magic Formula**: LRU Cache = `HashMap<Key, Node>` + Doubly Linked List.
- **Why Doubly Linked?**: A singly linked list can't delete an arbitrary node in `O(1)` because you don't know its `prev` node. Doubly linked gives you the `prev` node instantly.
- **Dummy Nodes**: ALWAYS use a dummy `head` and dummy `tail`. This completely eliminates messy edge cases (like checking if the list is empty or has only 1 element) when adding or removing nodes.
- **Store Key in Node**: The `Node` must store BOTH the `key` and the `value`. Why? Because when you evict the least recently used node from the tail, you need to delete it from the Hash Map too, which requires knowing its `key`!
- **Don't Forget to Update**: In `put`, if the key already exists, remember to **update the node's value** before moving it to the front!

## Similar problem

Some example problems with same pattern
- 460. LFU Cache 🧠
- 432. All O`one Data Structure 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
