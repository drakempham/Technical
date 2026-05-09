from typing import List


class RedundantConnection:
    class UnionFind:
        def __init__(self, n):
            self.parent = list(range(n+1))

        def find(self, x: int):
            if self.parent[x] != x:
                self.parent[x] = self.find(self.parent[x])
            return self.parent[x]

        def union(self, x, y):
            root_x = self.find(x)
            root_y = self.find(y)

            if root_x == root_y:
                return False
            self.parent[root_y] = root_x
            return True

    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        uf = self.UnionFind(n)
        for u, v in edges:
            if not uf.union(u, v):
                return [u, v]
        raise Exception("Something went wrong")


sol = RedundantConnection()
print(sol.findRedundantConnection([[1, 2], [2, 3], [1, 3]]))
