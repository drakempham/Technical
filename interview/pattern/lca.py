# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class LCAOfABinaryTree:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root or root == p or root == q:
            return root
        left_node = self.lowestCommonAncestor(root.left, p, q)
        right_node = self.lowestCommonAncestor(root.right, p, q)

        if left_node and right_node:
            return root
        return left_node if left_node else right_node


sol = LCAOfABinaryTree()
node = TreeNode(3)
node.left = TreeNode(5)
node.right = TreeNode(1)
node.left.left = TreeNode(6)
node.left.right = TreeNode(2)
node.left.right.left = TreeNode(7)
node.left.right.right = TreeNode(4)
node.right.left = TreeNode(0)
node.right.right = TreeNode(8)
p = node.left
q = node.left.right.right
print(sol.lowestCommonAncestor(node, p, q).val)
