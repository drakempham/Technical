import heapq
from typing import List


class KthLargest:

    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.pq = nums

        heapq.heapify(self.pq)
        # if we want max-heap
        # self.pq = [-x for x in nums]
        # heapq.heapify(self.pq)
        # and when return: - self.pq[0]

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


# k so nho hon ben trai, k so nho hon ben phai
class Solution:
    def kBigIndices(self, nums: List[int], k: int) -> int:
        n = len(nums)
        leftIndices = [False] * n
        rightIndices = [False] * n
        heap = []
        for i in range(n):
            if len(heap) == k and heap[0] > -nums[i]: # i lon hon k element heap
                leftIndices[i] = True

            heapq.heappush(heap, -nums[i])

            if len(heap) > k:
                heapq.heappop(heap)

        heap = []
        for i in range(n-1, -1, -1):
            if len(heap) == k and heap[0] > -nums[i]: # i lon hon k element heap
                rightIndices[i] = True

            heapq.heappush(heap, -nums[i])

            if len(heap) > k:
                heapq.heappop(heap)
        
        return sum(1 if leftIndices[i] == True and rightIndices[i] == True else 0 for i in range(n))

sol = Solution()
print(sol.kBigIndices([3,8,4,2,5,3,8,6],1))