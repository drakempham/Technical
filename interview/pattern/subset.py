from typing import List


class PartitionEqualSubsetSum:
    def canPartition(self, nums: List[int]) -> bool:
        total = sum(nums)

        if total % 2 == 1:
            return False
        target = total // 2
        dp = [False] * (target + 1)
        dp[0] = True
        for num in nums:
            for ele in range(target, num-1, -1):
                # check if ele co init duoc tu 1 mang toan num khong
                dp[ele] = dp[ele] or dp[ele-num]
        return dp[target]


sol = PartitionEqualSubsetSum()
print(sol.canPartition([1, 5, 12, 5]))
