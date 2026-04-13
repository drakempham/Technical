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
