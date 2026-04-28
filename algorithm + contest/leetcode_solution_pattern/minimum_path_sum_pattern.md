# Beginner Friendly ⛳️ || 2D DP 🗺️ || In-Place Optimization 🚀

## Approach 1: 2D Dynamic Programming

### Intuition

Imagine you are walking through a grid and trying to collect the fewest coins possible. Because you can only move **down** or **right**, the path to get to any specific cell `(i, j)` must have come from either the cell directly above it `(i - 1, j)` or the cell directly to its left `(i, j - 1)`. 

If we already know the minimum cost to reach the cell above and the cell to the left, we can simply pick the cheaper one and add the cost of our current cell!

### Why This Works

We are breaking down a massive pathfinding problem into tiny, single-step decisions. By solving the grid row by row from top to bottom, and column by column from left to right, we guarantee that whenever we look at the "top" or "left" neighbors, their absolute minimum cost has already been perfectly calculated. 

### Approach

- **Step 1: Initialize DP Table.** Create a 2D `dp` array of the same dimensions as the grid.
- **Step 2: Base Case.** The starting cell `dp[0][0]` is just `grid[0][0]` since we start there.
- **Step 3: First Row & Column.** 
  - Cells in the top row can only be reached by moving right, so we just keep adding the cost from the left cell.
  - Cells in the left-most column can only be reached by moving down, so we just keep adding the cost from the top cell.
- **Step 4: General Case.** For every other cell, look at the top and left neighbors. Take the minimum of those two, and add the current cell's value: `dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]`.
- **Step 5: Return Result.** The bottom-right cell `dp[m-1][n-1]` will hold our final answer.

### Complexity

- Time complexity: `O(M * N)` where `M` is the number of rows and `N` is the number of columns. We visit every cell exactly once.
- Space complexity: `O(M * N)` because we allocate a brand new 2D array to store our results.

### Code

**Python:**
```python
class Solution:
    def minPathSum(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[0] * n for _ in range(m)]
        
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    dp[i][j] = grid[i][j]
                elif i == 0:
                    # Can only come from the left
                    dp[i][j] = dp[i][j-1] + grid[i][j]
                elif j == 0:
                    # Can only come from above
                    dp[i][j] = dp[i-1][j] + grid[i][j]
                else:
                    # Can come from either left or above
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
                    
        return dp[m-1][n-1]
```

**Java:**
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
}
```

## Approach 2: In-Place DP (Constant Space)

### Intuition

Wait a minute, why do we need to create a whole new `dp` array? As we move through the grid, once we process a cell and calculate its minimum path sum, we never actually need to look at its *original* value ever again! We can just overwrite the input `grid` to save space.

### Why This Works

Overwriting the grid doesn't mess up any future calculations. When we are at cell `(i, j)`, we only look at `(i-1, j)` and `(i, j-1)`. Both of those cells have *already* been updated with their final minimum path sum. We just use those updated values to overwrite our current cell `(i, j)`.

### Approach

- The logic is exactly identical to Approach 1.
- The only difference is that instead of assigning values to `dp[i][j]`, we directly overwrite `grid[i][j]`.

### Complexity

- Time complexity: `O(M * N)` to visit every cell once.
- Space complexity: `O(1)` since we are modifying the input grid directly without allocating any new arrays.

### Code

**Python:**
```python
class Solution:
    def minPathSum(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    continue
                elif i == 0:
                    grid[i][j] += grid[i][j-1]
                elif j == 0:
                    grid[i][j] += grid[i-1][j]
                else:
                    grid[i][j] += min(grid[i-1][j], grid[i][j-1])
                    
        return grid[m-1][n-1]
```

**Java:**
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] += grid[i][j-1];
                } else if (j == 0) {
                    grid[i][j] += grid[i-1][j];
                } else {
                    grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        
        return grid[m-1][n-1];
    }
}
```

## Similar problem

Some example problems with same pattern
- 62. Unique Paths 🧠
- 63. Unique Paths II 🧠
- 120. Triangle 🧠
- 931. Minimum Falling Path Sum 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
