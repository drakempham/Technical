import heapq
from typing import List, Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        dummy = ListNode(0)
        head = dummy
        pq = []

        for i, node in enumerate(lists):
            if node:
                heapq.heappush(pq, (node.val, i, node))

        while pq:
            val, i, node = heapq.heappop(pq)

            head.next = ListNode(val)
            head = head.next

            if node.next:
                heapq.heappush(pq, (node.next.val, i, node.next))

        return dummy.next


sol = Solution()
lists = [ListNode(1, ListNode(4, ListNode(5))), ListNode(
    1, ListNode(3, ListNode(4))), ListNode(2, ListNode(6))]
result = sol.mergeKLists(lists)
while result:
    print(result.val, end=" ")
    result = result.next
