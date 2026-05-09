from collections import defaultdict
from typing import List


class SubArraySumEqualsK:
    def subarraySum(self, nums: List[int], k: int) -> int:
        prefixSum = [0] * (len(nums) + 1)
        prefixSum[0] = 0
        for i in range(1, len(nums) + 1):
            prefixSum[i] = prefixSum[i-1] + nums[i-1]

        count = defaultdict(int)
        ans = 0

        for curr in prefixSum:
            # culmutative curr count[0] with other previous element
            ans += count[curr - k]
            count[curr] += 1
        return ans

    def subarraySum2(self, nums: List[int], k: int) -> int:
        count = defaultdict(int)
        ans = 0
        curr_sum = 0
        count[0] = 1
        for num in nums:
            curr_sum += num
            ans += count[curr_sum - k]
            count[curr_sum] += 1
        return ans


sol = SubArraySumEqualsK()
# print(sol.subarraySum([1, 1, 1], 2))
print(sol.subarraySum2([1, -1, 0], 0))
