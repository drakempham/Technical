from typing import List


class HIndexII:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        left = 0
        right = len(citations) - 1
        while left <= right:
            mid = left + (right-left) // 2
            if citations[mid] >= n-mid:  # n-mid is h-index
                right = mid - 1
            else:
                left = mid + 1
        return n - left


sol = HIndexII()
print(sol.hIndex([0, 1, 3, 5, 6]))
print(sol.hIndex([1, 2, 100]))
