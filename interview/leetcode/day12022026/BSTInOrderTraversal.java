package day12022026;

import java.util.Stack;

public class BSTInOrderTraversal {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  private Stack<TreeNode> stack = new Stack<>();

  public BSTInOrderTraversal(TreeNode root) {
    if (root != null) {
      pushLeftNode(root);
    }
  }

  public int next() {
    TreeNode node = stack.pop();
    if (node.right != null) {
      pushLeftNode(node.right);
    }

    return node.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  private void pushLeftNode(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  public static void main(String[] args) {
    BSTInOrderTraversal bst = new BSTInOrderTraversal(new TreeNode(1, new TreeNode(2), new TreeNode(3)));
    System.out.println(bst.next());
    System.out.println(bst.hasNext());
    System.out.println(bst.next());

  }
}
