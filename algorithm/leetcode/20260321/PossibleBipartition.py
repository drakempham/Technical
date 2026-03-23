from collections import deque
from typing import List


class Solution:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        adj = [[] for _ in range(n+1)]
        for dislike in dislikes:
            adj[dislike[0]].append(dislike[1])
            adj[dislike[1]].append(dislike[0])

        group_pos = [None] * (n+1)
        for i in range(1, n+1):
            if group_pos[i] is None:
                group_pos[i] = True
                queue = deque([i])

                while queue:
                    curr_node = queue.popleft()
                    for neighbor in adj[curr_node]:
                        if group_pos[neighbor] == group_pos[curr_node]:
                            return False
                        if group_pos[neighbor] is None:
                            group_pos[neighbor] = not group_pos[curr_node]
                            queue.append(neighbor)

        return True


sol = Solution()
print(sol.possibleBipartition(4, [[1, 2], [1, 3], [2, 4]]))
