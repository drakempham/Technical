from typing import List


class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        result, max_len = 0, 0
        for i in range(len(nums)):
            if nums[i] == 1:
                max_len += 1
            else:
                result = max(result, max_len)
                max_len = 0
        return max(result, max_len)


solution = Solution()
print(solution.findMaxConsecutiveOnes([1, 1, 0, 1, 1, 1]))  # 3
