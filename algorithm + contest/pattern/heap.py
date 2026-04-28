import heapq
from typing import List


class KthLargest:

    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.pq = nums

        heapq.heapify(self.pq)

    def add(self, val: int) -> int:
        heapq.heappush(self.pq, val)
        while len(self.pq) > self.k:
            heapq.heappop(self.pq)
        return self.pq[0]


sol = KthLargest(3, [4, 5, 8, 2])

sol.add(3)
print(sol.pq)
sol.add(5)
print(sol.pq)
sol.add(10)
print(sol.pq)
sol.add(9)
print(sol.pq)
sol.add(4)
print(sol.pq)
