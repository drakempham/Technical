from typing import List


class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        # step 1: place 1..n in there correct positions( nums[i] -> index nums[i] - 1)
        for i in range(len(nums)):
            while nums[i] >= 1 and nums[i] <= len(nums) and nums[i] != i + 1:
                self.swap(nums, i)

        for i in range(len(nums)):
            if nums[i] != i + 1:
                return i+1

        return len(nums) + 1

    def swap(self, nums: List[int], i: int):
        temp = nums[i]
        nums[i] = nums[temp - 1]
        nums[temp - 1] = temp


solution = Solution()
print(solution.firstMissingPositive([3, 4, -1, 1]))  # 2
