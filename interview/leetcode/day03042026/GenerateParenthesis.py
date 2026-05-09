from typing import List


class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []

        def backtrack(n, left, right, currStr):
            if len(currStr) == 2 * n:
                res.append(currStr)
                return

            if left < n:
                backtrack(n, left+1, right, currStr + '(')
            if right < left:
                backtrack(n, left, right+1, currStr + ')')

        return backtrack(n, 0, 0, '')


sol = Solution()
print(sol.generateParenthesis(3))
