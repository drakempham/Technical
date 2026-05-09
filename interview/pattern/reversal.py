
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Reversal:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = None
        curr = head

        while curr:
            next_ptr = curr.next
            curr.next = prev
            prev = curr
            curr = next_ptr
        return prev


sol = Reversal()
node = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
print(sol.reverseList(node).val)
