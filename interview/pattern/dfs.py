class CheckifThereIsAValidPathInAGrid:
    def hasValidPath(self, grid: List[List[int]]) -> bool:
        path_dirs = { # use tuple to check existence of couple
            1: [(0,-1),(0,1)],
            2: [(-1,0),(1,0)],
            3: [(0,-1),(1,0)],
            4: [(0,1),(1,0)],
            5: [(-1,0),(0,-1)],
            6: [(-1,0),(0,1)]
        }

        visited = set()
        m, n = len(grid), len(grid[0])
        def dfs(r: int, c: int):
            if r == m-1 and c == n-1:
                return True

            visited.add((r,c))

            for dr, dc in path_dirs[grid[r][c]]:
                nr, nc = r + dr, c + dc

                if 0 <= nr < m and 0 <= nc < n:
                    if (nr, nc) in visited:
                        continue
                    if (-dr, -dc) not in path_dirs[grid[nr][nc]]:
                        continue
                    if dfs(nr, nc):
                        return True
            return False

        return dfs(0,0)


sol = CheckifThereIsAValidPathInAGrid()
print(sol.hasValidPath([[2,4,3], [6,5,2]]))