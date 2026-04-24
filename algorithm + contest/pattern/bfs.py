# Bài toán: Cho một ma trận chứa 0 (đường đi được) và 1 (tường chắn). Tìm số bước ngắn nhất từ góc trên trái (0,0) đến góc dưới phải (m-1, n-1). Nếu không đến được thì trả về -1.
# Python

from collections import deque
from typing import List


# class ShortestPathInBinaryMtrx:
#     def shortestPath(self, grid: list[list[int]]) -> int:
#         if not grid or grid[0][0] == 1 or grid[-1][-1] == 1:
#             return -1

#         visited = set()
#         queue = deque([(0, 0, 1)])
#         directions = [(-1, 0), (0, -1), (1, 0), (0, 1)]
#         m = len(grid)
#         n = len(grid[0])

#         visited.add((0, 0))
#         while queue:
#             curr_pos = queue.popleft()
#             if curr_pos[0] == m - 1 and curr_pos[1] == n - 1:
#                 return curr_pos[2]
#             for direction in directions:
#                 new_x = curr_pos[0] + direction[0]
#                 new_y = curr_pos[1] + direction[1]
#                 if 0 <= new_x < m and 0 <= new_y < n and (new_x, new_y) not in visited and grid[new_x][new_y] != 1:
#                     visited.add((new_x, new_y))
#                     queue.append((new_x, new_y, curr_pos[2] + 1))

#         return -1


# sol = ShortestPathInBinaryMtrx()
# grid = [[0, 1], [0, 0]]
# print(sol.shortestPath(grid))


class OpenTheLock:
    def openLock(self, deadends: List[str], target: str) -> int:
        dead_list = set(deadends)
        source = '0000'
        move = [-1, 1]
        queue = deque([(0, source)])
        visited = set()
        visited.add(source)

        while queue:
            curr_step = queue.popleft()
            if curr_step[1] == target:
                return curr_step[0]
            for i in range(len(source)):
                for j in range(len(move)):
                    new_step = (int(curr_step[1][i]) + move[j]) % 10
                    next_move = curr_step[1][:i] + \
                        str(new_step) + curr_step[1][i+1:]
                    if next_move in visited or next_move in dead_list:
                        continue
                    visited.add(next_move)
                    queue.append([curr_step[0] + 1, next_move])

        return -1


# sol = OpenTheLock()
# deadends = ["0201", "0101", "0102", "1212", "2002"]
# target = "0202"
# print(sol.openLock(deadends, target))

# deadends = ["8888"]
# target = "0009"
# print(sol.openLock(deadends, target))


class NearestExitFromEntracneInMaze:
    def nearestExit(self, maze: List[List[str]], entrance: List[int]) -> int:
        queue = deque([(0, entrance)])  # step, position
        # create {} breacker with at least one element
        visited = {tuple(entrance)}
        directions = [[-1, 0], [0, -1], [0, 1], [1, 0]]

        while queue:
            step, pos = queue.popleft()
            if (pos[0] == 0 or pos[0] == len(maze)-1 or pos[1] == 0 or pos[1] == len(maze[0])-1) and step != 0:
                return step
            for direction in directions:
                if 0 <= pos[0] + direction[0] < len(maze) and 0 <= pos[1] + direction[1] < len(maze[0]):
                    next_step = [pos[0] + direction[0], pos[1] + direction[1]]
                    if tuple(next_step) not in visited and maze[next_step[0]][next_step[1]] == '.':
                        visited.add(tuple(next_step))
                        queue.append((step + 1, next_step))
        return -1


sol = NearestExitFromEntracneInMaze()
# maze = [["+", "+", ".", "+"], [".", ".", ".", "+"], ["+", "+", "+", "."]]
# entrance = [1, 2]
# print(sol.nearestExit(maze, entrance))

maze = [[".", "+"]]
entrance = [0, 0]
print(sol.nearestExit(maze, entrance))


class MultiSourceFloodFill:
    def colorGrid(self, m: int, n: int, sources: List[List[int]]) -> List[List[int]]:
        ans = [([0] * (n)) for _ in range(m)]
        queue = deque()
        sources.sort(key=lambda x: -x[2])
        for r, c, color in sources:
            ans[r][c] = color
            queue.append((r, c, color))
        directions = [[-1, 0], [0, -1], [1, 0], [0, 1]]

        while queue:
            curr_r, curr_c, curr_color = queue.popleft()
            for r, c in directions:
                new_r = curr_r + r
                new_c = curr_c + c
                if 0 <= new_r < m and 0 <= new_c < n and ans[new_r][new_c] == 0:
                    ans[new_r][new_c] = curr_color
                    queue.append((new_r, new_c, curr_color))
        return ans


sol = MultiSourceFloodFill()
print(sol.colorGrid(3, 3, [[0, 0, 1], [2, 2, 2]]))
