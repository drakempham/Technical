import re
from typing import List
# https://leetcode.com/problems/range-addition-ii/?envType=problem-list-v2&envId=efe7b4us


class Solution:
    def maxCount(self, m: int, n: int, ops: List[List[int]]) -> int:
        if not ops:
            return m*n
        # maximum element in matrix are the number that exist in all operations
        # so we need to find the minimum row and minimum col from all operations (so it always exist in matrix)
        return min(op[0] for op in ops) * min(op[1] for op in ops)


solution = Solution()
print(solution.maxCount(3, 3, [[2, 2], [3, 3]]))
