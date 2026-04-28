from collections import defaultdict
from typing import Counter, List


class MinimumPathSUm:
    def minPathSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[float('inf') for _ in range(n)] for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    dp[i][j] = grid[i][j]
                elif i == 0:
                    dp[i][j] = dp[i][j-1] + grid[i][j]
                elif j == 0:
                    dp[i][j] = dp[i-1][j] + grid[i][j]
                else:
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        return dp[m-1][n-1]


sol = MinimumPathSUm()
print(sol.minPathSum([[1, 3, 1], [1, 5, 1], [4, 2, 1]]))


class WordBreak:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        n = len(s)
        dp= [False] * (n+1)
        dp[0] = True
        for i in range(1, n+1):
            for word in wordDict:
                if i >= len(word) and dp[i - len(word)] and s[i - len(word): i] == word:
                    dp[i] = True
                    break
        
        return dp[n]