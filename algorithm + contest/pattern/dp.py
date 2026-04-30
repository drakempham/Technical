from typing import List


class WildcardMatching:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n+1) for _ in range(m+1)]
        dp[0][0] = True
        for j in range(1, n+1):
            if p[j-1] == '*':
                dp[0][j] = dp[0][j-1]

        for i in range(1, m+1):
            for j in range(1, n+1):
                if p[j-1] == '?' or s[i-1] == p[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                elif p[j-1] == '*':
                    dp[i][j] = dp[i][j-1] or dp[i-1][j]

        return dp[m][n]


sol = WildcardMatching()
print(sol.isMatch("aa", "a"))


class MaximumPathScoreInGrid:
    # def maxPathScore(self, grid: List[List[int]], k: int) -> int:
    #     m, n = len(grid), len(grid[0])
    #     dp = [[[-1] * (k+1) for _ in range(n+1)] for _ in range(m+1)]
    #     value_to_cost_mapping = {
    #         0: (0, 0),
    #         1: (1, 1),
    #         2: (2, 1)
    #     }

    #     dp[0][1][0] = 0
    #     dp[1][0][0] = 0

    #     # new_cost = old_cost + cell_cost (cell_cost from 0 -> k)
    #     for i in range(1, m+1):
    #         for j in range(1, n+1):
    #             for cost in range(k+1):
    #                 cell_value, cell_cost = value_to_cost_mapping[
    #                     grid[i-1][j-1]]
    #                 new_cost = cost + cell_cost

    #                 if new_cost > k:
    #                     continue

    #                 if dp[i-1][j][cost] != -1:
    #                     dp[i][j][new_cost] = max(
    #                         dp[i][j][new_cost], dp[i-1][j][cost] + cell_value)

    #                 if dp[i][j-1][cost] != -1:
    #                     dp[i][j][new_cost] = max(
    #                         dp[i][j][new_cost], dp[i][j-1][cost] + cell_value)

    #     return max(dp[m][n])
    def maxPathScore(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])

        value_to_cost = {
            0: (0, 0),
            1: (1, 1),
            2: (2, 1),
        }

        dp = [[-1] * (k+1) for _ in range(n+1)]
        dp[1][0] = 0

        for i in range(1, m+1):
            new_dp = [[-1] * (k+1) for _ in range(n+1)]
            for j in range(1, n+1):
                # dem so cost cua o xet truoc do
                for cost in range(k+1):
                    curr_value, curr_cost = value_to_cost[grid[i-1][j-1]]
                    new_cost = cost + curr_cost

                    if new_cost > k:
                        break

                    # left - diem ben trai cung chi tinh tu top
                    # Không update lại old_cost.
                    if j > 1 and new_dp[j-1][cost] != -1:
                        new_dp[j][new_cost] = max(
                            new_dp[j][new_cost], new_dp[j-1][cost] + curr_value)

                    if dp[j][cost] != -1:
                        new_dp[j][new_cost] = max(
                            new_dp[j][new_cost], dp[j][cost] + curr_value)

            dp = new_dp
        return max(dp[n])


sol = MaximumPathScoreInGrid()
print(sol.maxPathScore([[0, 1, 1], [1, 1, 1]], 1))
print(sol.maxPathScore([[0, 1], [1, 2]], 1))


class CoinChange:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [float('inf')] * (amount+1)
        dp[0] = 0
        for i in range(1, amount + 1):
            for coin in coins:
                if coin <= i:
                    dp[i] = min(dp[i], dp[i-coin] + 1)
        return dp[amount] if dp[amount] != float('inf') else -1


sol = CoinChange()
print(sol.coinChange([2, 3], 13))
print(sol.coinChange([2], 3))
