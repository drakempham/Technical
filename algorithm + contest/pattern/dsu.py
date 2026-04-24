from collections import Counter, defaultdict
from typing import List


class DSU:
    def __init__(self, n: int):
        self.parent = list(range(n))

    def find(self, x: int):
        while self.parent[x] != x:
            x = self.parent[x]
        return x

    def union(self, a: int, b: int):
      # ban dau moi phan tu la root cua chinh no
        root_a = self.find(a)
        root_b = self.find(b)

        if root_a != root_b:
            self.parent[root_b] = root_a


n = 5
allowedSwaps = [[0, 1], [1, 2], [3, 4]]

dsu = DSU(n)

for a, b in allowedSwaps:
    dsu.union(a, b)
    print(f"after union({a}, {b}) -> {dsu.parent}")

for i in range(n):
    print(f"find root of ({i}) = {dsu.find(i)}")


class DSU:
    def __init__(self, n: int):
        self.parent = list(range(n))

    def find(self, x: int):
        while self.parent[x] != x:
            x = self.parent[x]
        return x

    def union(self, a: int, b: int):
      # ban dau moi phan tu la root cua chinh no
        root_a = self.find(a)
        root_b = self.find(b)

        if root_a != root_b:
            self.parent[root_b] = root_a


class MinimizeHammingDistanceAfterSwapOperations:

    def minimumHammingDistance(self, source: List[int], target: List[int], allowedSwaps: List[List[int]]) -> int:
        n = len(source)
        dsu = DSU(n)
        for i, j in allowedSwaps:
            dsu.union(i, j)
        graph = defaultdict(list)
        for i in range(n):
            root = dsu.find(i)
            graph[root].append(i)
        ans = 0
        for key in graph:
            counter = Counter()
            for idx in graph[key]:
                counter[source[idx]] += 1
            for idx in graph[key]:
                counter[target[idx]] -= 1
                if counter[target[idx]] == 0:
                    ans += 1
        return n - ans


sol = MinimizeHammingDistanceAfterSwapOperations()
print(sol.minimumHammingDistance([1, 2, 3, 4], [
      2, 1, 4, 5], [[0, 1], [0, 2]]))
