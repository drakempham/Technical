from typing import Optional

# Definition for singly-linked list.


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# Definition for a binary tree node.


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    '''
        findMid using slow and fast
        disconnect prev of slow immediately

        basecase:
        head = None-> none
        head.next = None -> TreeNode(head.val)
    '''

    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        if head is None:
            return None

        if head.next is None:
            return TreeNode(head.val)

        prev = None
        slow = head
        fast = head

        while fast and fast.next:
            prev = slow
            slow = slow.next
            fast = fast.next.next

        if prev:
            prev.next = None

        root = TreeNode(slow.val)

        if prev:
            root.left = self.sortedListToBST(head)
        if slow.next:
            root.right = self.sortedListToBST(slow.next)

        return root


sol = Solution()
head = ListNode(-10, ListNode(-3, ListNode(0, ListNode(5, ListNode(9)))))
result = sol.sortedListToBST(head)
# print the tree in pre-order


def print_tree(root):
    if root is None:
        return
    print(root.val, end=" ")
    print_tree(root.left)
    print_tree(root.right)


print_tree(result)
