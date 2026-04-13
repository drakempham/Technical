import heapq


class KthLargest:

    def __init__(self, k: int, nums: List[int]):
        self.k = k
        self.scores = []  # min_heap
        for num in nums:
            self.add(num)

    def add(self, val: int) -> int:
        heapq.heappush(self.scores, val)

        if len(self.scores) > self.k:
            heapq.heappop(self.scores)

        return self.scores[0]
