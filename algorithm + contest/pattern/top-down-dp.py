from functools import lru_cache
import time
from typing import List


class HouseRobber:

    def rob(self, nums: List[int]) -> int:
        @lru_cache(maxsize=None)
        def dp(i: int) -> int:
            if i >= len(nums):
                return 0
            return max(nums[i] + dp(i+2), dp(i+1))
        return dp(0)


start_time = time.perf_counter()
sol = HouseRobber()
end_time = time.perf_counter()
duration = (end_time - start_time) * 1000

print(str(sol.rob([1, 2, 3, 1])) + " duration: " + str(duration))
print(f"{duration:.4f}ms")
