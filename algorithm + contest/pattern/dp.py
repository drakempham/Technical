from typing import List


class HouseRobber:
    def rob(self, nums: List[int]) -> int:
        # rob and not_rob
        n = len(nums)
        rob = [0] * n
        not_rob = [0] * n

        rob[0] = nums[0]

        for i in range(1, len(nums)):
            rob[i] = not_rob[i-1] + nums[i]
            not_rob[i] = max(rob[i-1], not_rob[i-1])

        return max(rob[-1], not_rob[-1])
