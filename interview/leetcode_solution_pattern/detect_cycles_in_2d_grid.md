# DFS Cycle Detection 🔄 || Beginner Friendly ⛳️ || Step-by-Step Explanation ✨

## Intuition

Cycle detection in a 2D grid is very similar to detecting a cycle in an undirected graph. The core idea is to explore connected cells of the same character using Depth-First Search (DFS). If we ever encounter a cell that we've already visited — and it's **not the cell we just came from** — it means we've circled back, which proves a cycle exists!

## Why This Works

Imagine walking through a maze where you are only allowed to step on the letter 'a'. You leave a breadcrumb on every tile you step on. If you ever see a breadcrumb in front of you, you've walked in a circle! 

The only exception is the tile immediately behind you (your previous step). We prevent "U-turns" by simply passing the previous coordinates `(prev_r, prev_c)` into our DFS function and ignoring them when checking neighbors.

## Approach

- **Step 1:** Iterate through every cell in the grid. If a cell hasn't been visited yet, we use it as the starting point for our DFS.
- **Step 2:** In the DFS function, mark the current cell `(r, c)` as visited.
- **Step 3:** Explore all 4 adjacent directions. For each valid neighbor that shares the same character:
  - If the neighbor is exactly where we just came from `(prev_r, prev_c)`, ignore it.
  - If the neighbor has already been visited, a cycle is found! Return `True`.
  - Otherwise, continue the DFS recursively. If any recursive call returns `True`, propagate the `True` upwards.
- **Step 4:** If all cells are explored and no cycle is found, return `False`.

## Complexity

- **Time complexity:** `O(m * n)` where `m` is the number of rows and `n` is the number of columns. We visit each cell at most once because of our `visited` set.
- **Space complexity:** `O(m * n)` in the worst case (e.g., a massive spiral) for the recursion stack and the `visited` set.

## Code

**Python:**
```python
class Solution:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        directions = [[1, 0], [0, 1], [-1, 0], [0, -1]]
        m, n = len(grid), len(grid[0])
        visited = set()

        def dfs(prev_r, prev_c, r, c, char):
            visited.add((r, c))
            
            for dr, dc in directions:
                new_r, new_c = r + dr, c + dc
                
                # Check boundaries and if character matches
                if 0 <= new_r < m and 0 <= new_c < n and grid[new_r][new_c] == char:
                    # Prevent immediate U-turn
                    if (new_r, new_c) == (prev_r, prev_c):
                        continue
                        
                    # If we hit an already visited node, cycle found!
                    if (new_r, new_c) in visited:
                        return True
                        
                    # Continue DFS
                    if dfs(r, c, new_r, new_c, char):
                        return True

            return False

        for r in range(m):
            for c in range(n):
                # Start DFS from every unvisited cell
                if (r, c) not in visited and dfs(None, None, r, c, grid[r][c]):
                    return True
                    
        return False
```

**Java:**
```java
class Solution {
    private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private boolean[][] visited;
    private int m, n;
    private char[][] grid;

    public boolean containsCycle(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c]) {
                    if (dfs(-1, -1, r, c, grid[r][c])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int prevR, int prevC, int r, int c, char ch) {
        visited[r][c] = true;

        for (int[] dir : directions) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            if (newR >= 0 && newR < m && newC >= 0 && newC < n && grid[newR][newC] == ch) {
                if (newR == prevR && newC == prevC) {
                    continue; // Skip the parent cell
                }
                
                if (visited[newR][newC]) {
                    return true; // Cycle detected
                }
                
                if (dfs(r, c, newR, newC, ch)) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
