from collections import defaultdict
import sys
from typing import List, Optional


# class NumberOfProvinces:
#     def findCircleNum(self, isConnected: List[List[int]]) -> int:
#         ans = 0
#         visited = set()
#         n = len(isConnected)

#         def dfs(city: int):
#             for adjacent in range(n):
#                 if isConnected[city][adjacent] == 1 and adjacent not in visited:
#                     visited.add(adjacent)
#                     dfs(adjacent)
#         for city in range(n):
#             if city not in visited:
#                 dfs(city)
#                 ans += 1
#         return ans


# sol = NumberOfProvinces()
# print(sol.findCircleNum([[1, 1, 0], [1, 1, 0], [0, 0, 1]]))


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class MaximumDepthOfBinaryTree:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        def dfs(node: Optional[TreeNode]):
            if not node:
                return 0

            left = dfs(node.left)
            right = dfs(node.right)

            return 1 + max(left, right)

        return dfs(root)


class BalancedBinaryTree:
    def isBalanced(self, root: Optional[TreeNode]) -> int:
        def dfs(node: Optional[TreeNode]):
            if not node:
                return 0
            left_height = dfs(node.left)
            if left_height == -1:
                return -1
            right_height = dfs(node.right)
            if right_height == -1:
                return -1
            if abs(left_height - right_height) > 1:
                return -1
            return 1 + max(left_height, right_height)
        return dfs(root) != -1


class LCAOfABinaryTree:
    def lowestCommonAncestor(self, root:)


# sol = MaximumDepthOfBinaryTree()
# node = TreeNode(3)
# node.left = TreeNode(9)
# node.right = TreeNode(20)
# node.right.left = TreeNode(15)
# node.right.right = TreeNode(7)

# node = TreeNode(1)
# node.left = TreeNode(2)
# node.right = TreeNode(3)
# node.left.left = TreeNode(4)
# node.left.right = TreeNode(5)
# print(sol.maxDepth(node))

sol = BalancedBinaryTree()
node = TreeNode(1)
node.left = TreeNode(2)
node.right = TreeNode(3)
node.left.left = TreeNode(4)
node.left.right = TreeNode(5)
node.left.left.left = TreeNode(6)
print(sol.isBalanced(node))

class DetectCyclesIn2DGrid:
    def containsCycle(self, grid: List[List[str]]) -> bool:
        directions = [[1, 0], [0, 1], [-1, 0], [0, -1]]
        m, n = len(grid), len(grid[0])
        visited = set()
        stack = []

        def dfs(prev_r, prev_c, r, c, char):
            visited.add((r, c))
            stack.append([r, c])
            for basic_r, basic_c in directions:
                new_r = r + basic_r
                new_c = c + basic_c
                if 0 <= new_r < m and 0 <= new_c < n and grid[new_r][new_c] == char:
                    if (new_r, new_c) == (prev_r, prev_c):
                        continue
                    if (new_r, new_c) in visited:
                        return True  # found the cycle
                    if dfs(r, c, new_r, new_c, char):
                        return True

            return False

        for r in range(0, m):
            for c in range(0, n):
                if (r, c) not in visited and dfs(None, None, r, c, grid[r][c]):
                    return True
        return False


sol = DetectCyclesIn2DGrid()
print(sol.containsCycle([["a", "a", "a", "a"], ["a", "b", "b", "a"], [
      "a", "b", "b", "a"], ["a", "a", "a", "a"]]))



# There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

# You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the ith node, and an integer k.

# A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the resulting components all have values that are divisible by k, where the value of a connected component is the sum of the values of its nodes.

# Return the maximum number of components in any valid split.
class MaxiumNumberOfKDivisbleComponents:
    def maxKDivisibleComponents(self, n: int,
        edges: List[List[int]],
        values: List[int],
        k: int
    ) -> int:
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        parent = [-1] * n
        parent[0] = -2
        temp = []
        arr = [0]

        while arr:
            node = arr.pop()
            temp.append(node)

            for neighbor in graph[node]:
                if parent[neighbor] == -1:
                    parent[neighbor] = node
                    arr.append(neighbor)

        r = 0
        total = [v % k for v in values]

        for node in reversed(temp):
            if total[node] % k == 0:
                r += 1
            else:
                total[parent[node]] = (total[parent[node]] + total[node]) % k

        return r