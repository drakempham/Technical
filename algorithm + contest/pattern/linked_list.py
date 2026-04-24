# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

from typing import Optional
class Solution:
    def deleteNode(self, node):
        prev = node
        curr = node.next
        while curr.next:
            prev.val = curr.val
            prev = curr
            curr = curr.next
        prev.val = curr.val
        prev.next = None
        """
        :type node: ListNode
        :rtype: voia Do not return anything, modify node in-place instead.
        """

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class OddEventLinkedList:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return None
        odd_node = head
        even_node = head.next
        curr_even = even_node

        while even_node and even_node.next:
            odd_node.next = even_node.next
            odd_node = odd_node.next
            even_node.next = odd_node.next
            even_node = even_node.next
        odd_node.next = curr_even

        return head

sol = OddEventLinkedList()
head = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
new_head = sol.oddEvenList(head)

while new_head:
    print(new_head.val)
    new_head = new_head.next
    