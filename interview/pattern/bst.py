from typing import (List, Optional)


# class VerifyPreorderInBst:
#     # tu duy nay sai o cho la bst preorder co the duyet giam lien tuc
#     # def verify_preorder(self, preorder: List[int]) -> bool:
#     #     min_block = float('-inf')
#     #     max_block = float('inf')
#     #     stack = [preorder[0]]

#     #     for i in range(1, len(preorder)):
#     #         if preorder[i] > stack[-1]:
#     #             if preorder[i] > max_block:
#     #                 return False
#     #             max_block = preorder[i]
#     #         else:
#     #             if preorder[i] < min_block:
#     #                 return False
#     #             min_block = preorder[i]
#     #         stack.append(preorder[i])
#     #     return True
#     # co 2 yeu to:
#     # 1. khi da giam thi giam lien utc lduoc
#     # 2. mot khi da tang trong khi dang giam se xuat hien chan duoi  -> lower_bound = ancestor recently we meet
#     def verify_preorder(self, preorder: List[int]) -> bool:
#         stack = []
#         lower_bound = float('-inf')
#         for ele in preorder:
#             if ele < lower_bound:
#                 return False
#             while stack and ele > stack[-1]:
#                 lower_bound = stack.pop()  # find max lower bound
#             stack.append(ele)
#         return True


# sol = VerifyPreorderInBst()
# print(sol.verify_preorder([1, 3, 2, 4, 1]))

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class TwoSumBSTS:
    def twoSumBSTS(self, root1: Optional[TreeNode], root2: Optional[TreeNode], target: int) -> bool:
        root1_traversal = set()

        def inOrder(node: Optional[TreeNode]):
            if not node:
                return
            inOrder(node.left)
            root1_traversal.add(node.val)
            inOrder(node.right)

        def check(node: Optional[TreeNode]):
            if not node:
                return False
            if target - node.val in root1_traversal:
                return True
            return check(node.left) or check(node.right)

        inOrder(root1)
        return check(root2)

    def twoSumBSTSWithInOrder(self, root1: Optional[TreeNode], root2: Optional[TreeNode], target: int) -> bool:
        nums = [[], []]

        def inOrder(node: Optional[TreeNode], i: int):
            if not node:
                return
            inOrder(node.left, i)
            nums[i].append(node.val)
            inOrder(node.right, i)
        inOrder(root1, 0)
        inOrder(root2, 1)
        i = 0
        j = len(nums[1]) - 1
        while 0 <= i < len(nums[0]) and 0 <= j < len(nums[1]):
            total = nums[0][i] + nums[1][j]
            if total == target:
                return True
            if total < target:
                i += 1
            else:
                j -= 1
        return False


sol = TwoSumBSTS()
root1 = TreeNode(2)
root1.left = TreeNode(1)
root1.right = TreeNode(4)
root2 = TreeNode(1)
root2.left = TreeNode(0)
root2.right = TreeNode(3)
print(sol.twoSumBSTSWithInOrder(root1, root2, 5))
