from typing import List

class MaximumScoreFromGridOpts:
    def maximumScore(self, grid: List[List[int]]) -> int:
        n = len(grid)
        prefixSum = [[0] * (n+1) for _ in range(n+1)]

        for col in range(n):
            for row in range(n):
                prefixSum[col][row + 1] = prefixSum[col][row] + grid[row][col]

        def gainScore(col, prev, curr, nxt):
            max_neighbor = max(prev, nxt)
            if max_neighbor <= curr:
                return 0
            return prefixSum[col][max_neighbor] - prefixSum[col][curr]

        dp = [[-1] * (n+1) for _ in range(n+1)]
        for h0 in range(n+1):
            for h1 in range(n+1):
                dp[h0][h1] = gainScore(0, 0, h0, h1)

        for col in range(1, n-1):
            new_dp = [[-1] * (n+1) for _ in range(n+1)]

            for prev in range(n+1):
                for curr in range(n+1):
                    if dp[prev][curr] == -1:
                        continue
                    for nxt in range(n+1):
                        new_dp[curr][nxt] = max(new_dp[curr][nxt], dp[prev][curr] + gainScore(col, prev, curr, nxt))

            dp = new_dp

        total = 0
        for prev in range(n+1):
            for curr in range(n+1):
                score = dp[prev][curr] + gainScore(n-1, prev, curr, 0)
                total = max(total, score)

        return total

sol = MaximumScoreFromGridOpts()
print(sol.maximumScore([[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]))
