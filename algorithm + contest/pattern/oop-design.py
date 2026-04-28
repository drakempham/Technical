class Node:
  def __init__(self, key=0, val=0, freq = 1):
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

  def add_to_front(self, node:Node):
    first_node = self.head.next

    node.prev = self.head
    node.next = first_node

    self.head.next = node
    first_node.prev = node
  def remove(self, node:Node):
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

  def update_freq(self, node:Node):
    old_freq = node.freq
    old_list = self.freq_to_doublyLinkedList.get(old_freq)
    old_list.remove(node)

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
    
    # EVICT FIRST if at capacity
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

# ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
# [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]

sol = LFUCache(2)
sol.put(1, 1)
sol.put(2, 2)
print(sol.get(1))      # returns 1
sol.put(3, 3)
print(sol.get(2))      # returns -1 (not found)
print(sol.get(3))      # returns 3
sol.put(4, 4)
print(sol.get(1))      # returns -1 (not found)
print(sol.get(3))      # returns 3
print(sol.get(4))      # returns 4