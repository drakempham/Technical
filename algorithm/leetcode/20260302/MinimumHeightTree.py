from collections import defaultdict, deque
from tracemalloc import start
from typing import List


class Solution:
    # syntax: defaultdict(list)
    # queue = deque() . popleft vs appendLeft
    # visited = set()
    # res = [point] override arr

    # O(n2)
    def findMinHeightTrees(self, n: int, edges: List[List[int]]) -> List[int]:
        if n == 1:
            return [0]

        tree = defaultdict(list)
        minHeight = float('inf')
        res = []

        for edge in edges:
            tree[edge[0]].append(edge[1])
            tree[edge[1]].append(edge[0])

        def bfs(point: int) -> int:

            queue = deque()
            queue.append(point)
            height = 0
            visited = set()
            visited.add(point)

            while queue:
                level_size = len(queue)
                for _ in range(level_size):
                    node = queue.popleft()
                    for neighbor in tree[node]:
                        if neighbor not in visited:
                            visited.add(neighbor)
                            queue.append(neighbor)
                height += 1

            return height

        for point in tree:
            currHeight = bfs(point)

            if currHeight == minHeight:
                res.append(point)
            elif minHeight > currHeight:
                minHeight = currHeight
                res = [point]
        return res

    # O(n) with onion peeling - come from outside to inside
    def onionPeeling(self, n: int, edges: List[List[int]]) -> List[int]:
        if n == 1:
            return [0]  # 0 -> n-1
        tree = defaultdict(set)  # for removing O(1)
        for edge in edges:
            tree[edge[0]].add(edge[1])
            tree[edge[1]].add(edge[0])

        total = len(tree)

        leafNodes = deque()
        for key in tree:
            if len(tree[key]) == 1:
                leafNodes.append(key)
        # if remaining node = 1, it is a star
        # if = 2, it is a dumpbell
        while total > 2:
            total -= len(leafNodes)
            size = len(leafNodes)
            for _ in range(size):
                node = leafNodes.popleft()
                for neighbor in tree[node]:
                    tree[neighbor].remove(node)
                    if len(tree[neighbor]) == 1:
                        leafNodes.append(neighbor)

        # last leaf nodes is center
        res = []
        for key in leafNodes:
            res.append(key)

        return res


sol = Solution()

edges = [[1, 0], [1, 2], [1, 3]]
n = 4
# print(sol.findMinHeightTrees(n, edges))
print(sol.onionPeeling(n, edges))
