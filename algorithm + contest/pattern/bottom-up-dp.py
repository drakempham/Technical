class Solution:
    def rob(self, nums: List[int]) -> int:
        prev2 = 0
        prev1 = nums[0]

        for i in range(1, len(nums)):
            curr = max(prev2 + nums[i], prev1)
            prev2 = prev1
            prev1 = curr
        return prev1


sol =
