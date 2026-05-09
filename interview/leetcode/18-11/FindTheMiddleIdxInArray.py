from typing import List


class Solution:
    def findMiddleIndex(self, nums: List[int]) -> int:
        total = sum(nums)
        left = 0
        right = total

        for i in range(len(nums)):
            if left == right - nums[i]:
                return i
            left += nums[i]
            right -= nums[i]

        return -1

    def optimize(self, nums: List[int]) -> int:
        total = sum(nums)
        left = 0
        for i, ele in enumerate(nums):
            if left == total - left - ele:
                return i
            left += ele
        return -1


solution = Solution()
print(solution.findMiddleIndex([2, 3, -1, 8, 4]))  # 3
