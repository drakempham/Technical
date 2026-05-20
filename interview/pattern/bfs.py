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

from collections import defaultdict
from typing import List
from collections import deque
class Solution:
    # O(N*M2)
    # def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
    #     if endWord not in word_set:
    #         return 0
        
    #     queue = deque([beginWord])
    #     change = 1
    #     visited = set()
    #     visited.add(beginWord)
    #     word_set = set(wordList)

    #     while queue: # N (len of list)
    #         curr_len =  len(queue)
    #         for _ in range(curr_len):
    #             curr_word = queue.popleft()
    #             if curr_word == endWord:
    #                 return change
    #             for i in range(len(curr_word)): # M ( len of word)
    #                 for j in range(0, 26): # 26
    #                     next_c = chr(ord('a') + j)
    #                     next_word = curr_word[:i] + next_c + curr_word[i+1:] # M
    #                     if next_word in word_set and next_word not in visited:
    #                         visited.add(next_word)
    #                         queue.append(next_word)
    #         change += 1
    #     return 0
    
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        if endWord not in wordList:
            return []
        n = len(endWord)

        pattern_to_word = defaultdict(list)
        for word in wordList:
            for i in range(n):
                pattern = word[:i] + '*'+word[i+1:]
                pattern_to_word[pattern].append(word)
        
        visited = set([beginWord])
        queue = deque([(beginWord, 1)])
        while queue:
            curr_word, curr_ops = queue.popleft()
            for i in range(n):
                pattern = curr_word[:i] + '*' + curr_word[i+1:]

                for word in pattern_to_word[pattern]:

                    if word == endWord:
                        return curr_ops + 1

                    if word not in visited:
                        queue.append((word, curr_ops + 1))
                        visited.add(word)
                
                if pattern_to_word[pattern]:
                    del pattern_to_word[pattern]
        return 0







    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        if endWord not in wordList:
            return []
        n = len(endWord)

        pattern_to_word = defaultdict(list)
        for word in wordList:
            for i in range(n):
                pattern = word[:i] + '*'+word[i+1:]
                pattern_to_word[pattern].append(word)
        
        queue = deque([(beginWord)])
        dist = {beginWord: 1}
        parent_node= defaultdict(list)

        while queue:
            curr_word = queue.popleft()
            for i in range(n):
                pattern = curr_word[:i] + '*' + curr_word[i+1:]

                for adj in pattern_to_word[pattern]:

                    if adj not in dist:
                        dist[adj] = dist[curr_word] + 1
                        parent_node[adj].append(curr_word)
                        queue.append(adj)
                    else: # check another path
                        if dist[adj] == dist[curr_word] + 1:
                            parent_node[adj].append(curr_word)
        res = []
        if endWord in parent_node:
            def dfs(word: str, path: List[str]):
                if word == beginWord:
                    res.append(path[::-1])
                    return
                
                for parent in parent_node[word]:
                    path.append(parent)
                    dfs(parent, path)
                    path.pop()
            dfs(endWord, [endWord])
        return res



sol = Solution()
# print(sol.ladderLength("hit", "cog", ["hot","dot","dog","lot","log","cog"]))
print(sol.findLadders("hit", "cog", ["hot","dot","dog","lot","log","cog"]))


from collections import deque
from collections import defaultdict
from typing import List
class Solution:
    # tim con duong ngan nhat tren canh ko trong so -> bfs
    def minJumps(self, arr: List[int]) -> int:
        val_to_pos = defaultdict(list)
        for idx, num in enumerate(arr):
            val_to_pos[num].append(idx)
        n = len(arr)
        queue = deque([0])
        dist = {0: 0}
        while queue:
            curr_pos = queue.popleft()
            if curr_pos == n-1:
                return dist[curr_pos]
            for idx in (curr_pos -1, curr_pos + 1):
                if 0<=idx<n and idx not in dist.keys():
                    queue.append(idx)
                    dist[idx] = dist[curr_pos] + 1
            for idx in val_to_pos[arr[curr_pos]]:
                if 0<=idx<n and idx not in dist.keys():
                    queue.append(idx)
                    dist[idx] = dist[curr_pos] + 1
        return -1

sol = Solution()
print(sol.minJumps([100,-23,-23,404,100,23,23,23,3,404]))
