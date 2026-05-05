# Circular Linking the List || Beginner Friendly ⛳️ || Linked List

## Intuition

When rotating a linked list by `k` steps, the last `k` nodes effectively move to the front of the list. However, if `k` is larger than the total length of the list, we only need to perform `k % length` rotations, because rotating by the list's length brings it right back to its original state. 

Instead of dealing with multiple disconnected pieces, a great trick is to connect the tail of the list back to the head, forming a **circular linked list**. Once it's circular, we just need to find the node that should become the new tail, break the circle there, and return the next node as the new head.

## Approach

- **Step 1:** Handle edge cases. If the list is empty, has only one node, or `k == 0`, we simply return the `head`.
- **Step 2:** Traverse the list once to find its total `length` and get a pointer to the original `tail`.
- **Step 3:** Connect `tail.next` to `head`. Now the list is a circle.
- **Step 4:** Calculate the effective number of rotations using `k = k % length`. The new tail will be located exactly `length - k - 1` steps from the original head. We traverse forward to find this `new_tail`.
- **Step 5:** The new head is simply `new_tail.next`. We break the circle by setting `new_tail.next = None` and return the `new_head`.

## Complexity

- Time complexity: `O(N)`
  We traverse the list once to find its length, and at most once more to find the new tail. Overall time is linear.
- Space complexity: `O(1)`
  We only use a few variables for pointers (`tail`, `new_tail`, `length`), which requires constant extra space.

## Code

**Python:**

```python
class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        # Edge cases: empty list, single node, or no rotation needed
        if not head or not head.next or k == 0:
            return head
        
        # Step 1 & 2: Find the length and the original tail
        length = 1
        tail = head
        while tail.next:
            length += 1
            tail = tail.next
            
        # Step 3: Form a circle by connecting tail to head
        tail.next = head
        
        # Step 4: Find the new tail, which is (length - k % length - 1) steps from head
        k = k % length
        steps_to_new_tail = length - k - 1
        
        new_tail = head
        for _ in range(steps_to_new_tail):
            new_tail = new_tail.next
            
        # Step 5: Break the circle
        new_head = new_tail.next
        new_tail.next = None
        
        return new_head
```

## Similar Problem

Some example problems with same pattern
- 189. Rotate Array 🧠
- 24. Swap Nodes in Pairs 🧠
- 328. Odd Even Linked List 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
