public class IsBalanced {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isBalanced(TreeNode root) {
    return checkBalance(root) != Integer.MIN_VALUE;
  }

  public int checkBalance(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftHeight = checkBalance(root.left);
    int rightHeight = checkBalance(root.right);
    if (leftHeight == Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }

    if (rightHeight == Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return Integer.MIN_VALUE;
    }

    return 1 + Math.max(leftHeight, rightHeight);
  }

  public static void main(String[] args) {
    IsBalanced solution = new IsBalanced();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.left.left.left = new TreeNode(6);

    boolean balanced = solution.isBalanced(root);
    System.out.println("Is Balanced: " + balanced); // Output: false
  }
}
