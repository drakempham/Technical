import heapq
import math
from typing import List


class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        max_heap = [-p for p in piles]
        heapq.heapify(max_heap)

        for t in range(k):
            max_ele = -heapq.heappop(max_heap)

            max_ele -= math.floor(max_ele / 2)

            heapq.heappush(max_heap, -max_ele)

        return - sum(max_heap)

    def minStoneSumWithMaxValue(self, piles: List[int], k: int) -> int:
        max_val = max(piles)
        frequency = [0] * (max_val+1)
        for p in piles:
            frequency[p] += 1

        while max_val > 1 and k > 0:
            if frequency[max_val] > 0:
                ops = min(k, frequency[max_val])

                new_val = max_val - math.floor(max_val / 2)

                frequency[max_val] -= ops
                frequency[new_val] += ops
                k -= ops

            if frequency[max_val] == 0:
                max_val -= 1

        max_sum = 0
        for ele, idx in enumerate(frequency):
            if frequency[ele] > 0:
                max_sum += ele * frequency[ele]

        return max_sum


sol = Solution()
# print(sol.minStoneSum([5, 4, 9], 2))
# print(sol.minStoneSumWithMaxValue([5, 4, 9], 2))
print(sol.minStoneSumWithMaxValue([4, 3, 6, 7], 3))
