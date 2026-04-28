# Beginner Friendly ⛳️ || DFS Grid Traversal 🗺️ || Pipe Connection 🪠

## Intuition

This problem asks us to find if there's a valid path from the top-left to the bottom-right of a grid. But unlike typical grid problems where we can move freely in all 4 directions, our movement is strictly restricted by "pipes" or "streets" (represented by numbers 1 to 6). 

Think of it like laying down train tracks. For a train to move smoothly from track A to track B, track A must have an opening pointing towards track B, **AND** track B must have an opening pointing directly back towards track A. If track A goes Right, but track B only goes Up and Down, the train derails!

## Why This Works

1. **Direction Mapping**: Instead of writing massive, confusing `if/else` statements for all 6 pipe types, we can cleanly map each pipe type to its possible movement directions `(dr, dc)`.
2. **The "Connect Back" Rule**: This is the core logic. When moving from our current cell with a specific direction, the new cell **MUST** have a pipe that goes in the exact opposite direction to form a valid, continuous connection. 
3. **Depth First Search (DFS)**: We explore a single path as far as possible. Since each pipe only has exactly 2 openings, there are virtually no branches, making DFS incredibly fast and efficient for this problem.

## Approach

- **Step 1: Map the Pipes.** Create a map or 3D array where the index is the pipe type (1-6) and the value is a list of tuples representing its open directions. For example, type 1 is Left `(0, -1)` and Right `(0, 1)`.
- **Step 2: Initialize DFS.** Start our search from the top-left corner `(0, 0)`. Keep track of `visited` cells to avoid walking in infinite loops.
- **Step 3: Base Case.** If our current row and column match the bottom-right corner, we successfully found a valid path! Return `True`.
- **Step 4: Explore Neighbors.** Look at the allowed directions for our current pipe. Calculate the coordinate of the next cell.
- **Step 5: Connection Validation.** Before we step into the next cell, we must check three crucial things:
  1. Is it within the grid boundaries?
  2. Is it unvisited?
  3. Does the pipe at the next cell have an opening pointing back at us?
- **Step 6: Recursion.** If all checks pass, recursively call DFS. If it eventually returns `True`, bubble up the success all the way to the start.

## Complexity

- Time complexity: `O(M * N)` where `M` is the number of rows and `N` is the number of columns. In the worst-case scenario, we might visit every cell in the grid exactly once.
- Space complexity: `O(M * N)` for the recursion stack and the `visited` set (e.g., if the path looks like a long, winding snake covering the entire grid).

## Code

**Python:**
```python
class Solution:
    def hasValidPath(self, grid: list[list[int]]) -> bool:
        # Map each pipe type to its open directions (dr, dc)
        path_dirs = { 
            1: [(0, -1), (0, 1)],   # Left, Right
            2: [(-1, 0), (1, 0)],   # Up, Down
            3: [(0, -1), (1, 0)],   # Left, Down
            4: [(0, 1), (1, 0)],    # Right, Down
            5: [(-1, 0), (0, -1)],  # Up, Left
            6: [(-1, 0), (0, 1)]    # Up, Right
        }

        visited = set()
        m, n = len(grid), len(grid[0])

        def dfs(r: int, c: int) -> bool:
            # Base Case: Reached the bottom-right corner
            if r == m - 1 and c == n - 1:
                return True

            visited.add((r, c))

            # Try moving through the openings of the current pipe
            for dr, dc in path_dirs[grid[r][c]]:
                nr, nc = r + dr, c + dc

                if 0 <= nr < m and 0 <= nc < n:
                    if (nr, nc) in visited:
                        continue
                    
                    # The next cell MUST have an opening pointing BACK to us
                    if (-dr, -dc) not in path_dirs[grid[nr][nc]]:
                        continue
                    
                    # If valid connection, continue DFS
                    if dfs(nr, nc):
                        return True
                        
            return False

        return dfs(0, 0)
```

**Java:**
```java
class Solution {
    public boolean hasValidPath(int[][] grid) {
        // Map each pipe type to its open directions (dr, dc)
        int[][][] pathDirs = {
            {}, // 1-indexed, so 0 is empty
            {{0, -1}, {0, 1}},   // 1: Left, Right
            {{-1, 0}, {1, 0}},   // 2: Up, Down
            {{0, -1}, {1, 0}},   // 3: Left, Down
            {{0, 1}, {1, 0}},    // 4: Right, Down
            {{-1, 0}, {0, -1}},  // 5: Up, Left
            {{-1, 0}, {0, 1}}    // 6: Up, Right
        };
        
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        return dfs(0, 0, grid, visited, pathDirs);
    }
    
    private boolean dfs(int r, int c, int[][] grid, boolean[][] visited, int[][][] pathDirs) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Base Case: Reached the bottom-right corner
        if (r == m - 1 && c == n - 1) {
            return true;
        }
        
        visited[r][c] = true;
        int pipeType = grid[r][c];
        
        // Try moving through the openings of the current pipe
        for (int[] dir : pathDirs[pipeType]) {
            int dr = dir[0];
            int dc = dir[1];
            int nr = r + dr;
            int nc = c + dc;
            
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                int nextPipeType = grid[nr][nc];
                boolean canConnectBack = false;
                
                // The next cell MUST have an opening pointing BACK to us
                for (int[] nextDir : pathDirs[nextPipeType]) {
                    if (nextDir[0] == -dr && nextDir[1] == -dc) {
                        canConnectBack = true;
                        break;
                    }
                }
                
                // If valid connection, continue DFS
                if (canConnectBack) {
                    if (dfs(nr, nc, grid, visited, pathDirs)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
