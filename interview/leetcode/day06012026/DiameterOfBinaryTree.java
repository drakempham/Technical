public class DiameterOfBinaryTree {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private int diameter;

  public int diameterOfBinaryTree(TreeNode root) {
    findDiameter(root);
    return diameter;
  }

  public int findDiameter(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftHeight = findDiameter(root.left);
    int rightHeight = findDiameter(root.right);

    diameter = Math.max(diameter, leftHeight + rightHeight); // diameter is longest edge

    return 1 + Math.max(leftHeight, rightHeight);
  }

  public static void main(String[] args) {
    DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    int diameter = solution.diameterOfBinaryTree(root);
    System.out.println("Diameter of Binary Tree: " + diameter); // Output: 3s
  }
}
