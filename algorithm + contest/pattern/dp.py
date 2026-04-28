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
        dp = [False] * (n+1)
        dp[0] = True
        for i in range(1, n+1):
            for word in wordDict:
                if i >= len(word) and dp[i - len(word)] and s[i - len(word): i] == word:
                    dp[i] = True
                    break

        return dp[n]


class SolvingQuestionWithBrainPower:
    # dp[i] most points can receive when solve questions from i -> n
    def mostPoints(self, questions: List[List[int]]) -> int:
        n = len(questions)
        dp = [0] * (n+1)  # space for the skip of n-1
        for i in range(n-1, -1, -1):
            point, brainpower = questions[i]

            total_solved = point

            next_question = i + brainpower + 1
            if next_question < n:
                total_solved += dp[next_question]

            dp[i] = max(total_solved, dp[i+1])
        return dp[0]


sol = SolvingQuestionWithBrainPower()
print(sol.mostPoints([[3, 2], [4, 3], [4, 4], [2, 5]]))
