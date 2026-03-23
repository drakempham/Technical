from collections import deque
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
   # can skip many one so this is not should be
    # def rob(self, root: Optional[TreeNode]) -> int:
    #     if not root:
    #         return 0
    #     queue = deque()
    #     queue.append(root)
    #     isEven = True
    #     oddSum, evenSum = 0, 0

    #     while queue:
    #         n = len(queue)
    #         for i in range(n):
    #             node = queue.popleft()
    #             if node.left:
    #                 queue.append(node.left)
    #             if node.right:
    #                 queue.append(node.right)
    #             if isEven:
    #                 evenSum += node.val
    #             else:
    #                 oddSum += node.val
    #         isEven = not isEven
    #     return max(oddSum, evenSum)

    # at each node, we have two options: take or skip it
    def rob(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        def dfs(root: Optional[TreeNode]) -> List[int]:
            if not root:
                return [0, 0]
            left = dfs(root.left)
            right = dfs(root.right)

            # in the skip, don't just take the left[0] + right[0] because we not know if at that position, skip or rob larger
            return [left[1] + right[1] + root.val, max(left) + max(right)]

        take_it, skip_it = dfs(root)

        return max(take_it, skip_it)


sol = Solution()

# root = TreeNode(3)
# root.left = TreeNode(2)
# root.right = TreeNode(3)
# root.left.right = TreeNode(3)
# root.right.right = TreeNode(1)

# print(sol.rob(root))


# root_2 = TreeNode(3)
# root_2.left = TreeNode(4)
# root_2.right = TreeNode(5)
# root_2.left.left = TreeNode(1)
# root_2.left.right = TreeNode(3)
# root_2.right.right = TreeNode(1)

# print(sol.rob(root_2))

root = TreeNode(4)
root.left = TreeNode(1)
root.left.left = TreeNode(2)
root.left.left.left = TreeNode(3)

print(sol.rob(root))
