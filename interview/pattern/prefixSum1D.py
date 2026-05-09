from collections import defaultdict
from typing import List


class RangeSumQueryImmutable:

    def __init__(self, nums: List[int]):
        self.n = len(nums)
        self.sum = [0] * (self.n + 1)
        self.sum[0] = 0
        for i in range(1, len(self.sum)):
            self.sum[i] = self.sum[i-1] + nums[i-1]

    def sumRange(self, left: int, right: int) -> int:
        return self.sum[right+1] - self.sum[left]


sol = RangeSumQueryImmutable([-2, 0, 3, -5, 2, -1])
print(sol.sumRange(0, 2))


class SumOfDistances:

    # x gia su o vi tri k '
    # arr[x] = (x-arr[0]) + (x-arr[1]) + .. + (x-arr[k-1]) + 0 + (arr[k+11] - x) + ..+(arr[n-1] - x)
    # = kx - (arr[0] + .. arr[k-1]) + 0 + (arr[k+1] + .. + arr[n-1]) - (n-k-1)x
    # = kx - prefix_sum + (suffix_sum) - (n-k-1)x
    # = kx - prefix_sum + (sum-x-prefix_sum) - (n-k-1)x

    def distance(self, nums: List[int]) -> List[int]:
        pos_arr = defaultdict(list)
        for i, num in enumerate(nums):
            pos_arr[num].append(i)
        ans = [0] * len(nums)
        # num in pos_arr, positions in pos_arr.values(), num, position in pos_arr.items()
        for num, positions in pos_arr.items():
            prefix_sum = 0
            total = sum(positions)
            n = len(positions)
            for i, pos in enumerate(positions):
                left_sum = (i*pos) - prefix_sum
                right_sum = (total - pos - prefix_sum) - (n - i - 1)*pos
                ans[pos] = left_sum + right_sum
                prefix_sum += pos
        return ans


sol = SumOfDistances()
print(sol.distance([1, 3, 1, 1, 2]))
