# O(n*k), beats 90% memory || Beginner Friendly ⛳️ || Grid Dynamic Programming || Budget State

## Intuition

We are moving through a grid from the top-left cell to the bottom-right cell. Each cell gives us a score, but some cells also consume cost. We want the maximum score while keeping the total cost at most `k`.

A normal grid DP only needs to remember the best answer for each cell. Here, that is not enough, because reaching the same cell with different costs can lead to different future results.

So we add one more DP dimension:

`dp[j][cost] = maximum score when we reach column j using exactly cost`

Since we process the grid row by row, we only need the previous row and the current row.

## Why This Works

For every cell, the path can only come from two directions: top or left.

The important invariant is:

`dp[j][cost]` stores the best score possible for the current processed row at column `j` with total cost `cost`.

When we enter a cell, we add that cell's score and cost. If the new cost is still within `k`, we update the answer from:

- the top cell: `prev[j][old_cost]`
- the left cell: `curr[j - 1][old_cost]`

Because the grid is processed from top to bottom and left to right, both of these states are already computed before we use them.

## Approach

- **Step 1:** Let `m` and `n` be the grid dimensions.
- **Step 2:** Cap `k` by `m + n - 1`, because any valid path visits exactly `m + n - 1` cells.
- **Step 3:** Use `prev[j][cost]` to store results from the previous row.
- **Step 4:** For each row, create `curr[j][cost]` for the current row.
- **Step 5:** For each cell `(i, j)`, calculate its `score` and `cost`.
- **Step 6:** Handle the starting cell separately.
- **Step 7:** Try moving from the top and from the left for every old cost.
- **Step 8:** Return the maximum value at the bottom-right cell. If it is unreachable, return `-1`.

## Complexity

- Time complexity: `O(m * n * k)`, because each cell checks every possible cost from `0` to `k`.
- Space complexity: `O(n * k)`, because we only keep the previous row and current row.

## Code

**Python:**

```python
from typing import List


class Solution:
    def maxPathScore(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        k = min(k, m + n - 1)

        prev = [[-1] * (k + 1) for _ in range(n + 1)]

        for i in range(1, m + 1):
            curr = [[-1] * (k + 1) for _ in range(n + 1)]

            for j in range(1, n + 1):
                score = grid[i - 1][j - 1]
                cost = 0 if score == 0 else 1

                if i == 1 and j == 1:
                    if cost <= k:
                        curr[j][cost] = score
                    continue

                for old_cost in range(k + 1):
                    new_cost = old_cost + cost
                    if new_cost > k:
                        continue

                    if prev[j][old_cost] != -1:
                        curr[j][new_cost] = max(
                            curr[j][new_cost],
                            prev[j][old_cost] + score
                        )

                    if curr[j - 1][old_cost] != -1:
                        curr[j][new_cost] = max(
                            curr[j][new_cost],
                            curr[j - 1][old_cost] + score
                        )

            prev = curr

        answer = max(prev[n])
        return answer if answer != -1 else -1
```

## Similar Problem

Some example problems with same pattern
- 62. Unique Paths 🧠
- 64. Minimum Path Sum 🧠
- 174. Dungeon Game 🧠
- 2304. Minimum Path Cost in a Grid 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
