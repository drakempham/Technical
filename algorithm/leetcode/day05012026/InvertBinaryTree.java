public class InvertBinaryTree {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
      left = null;
      right = null;
    }

    TreeNode(int x, TreeNode left, TreeNode right) {
      val = x;
      this.left = left;
      this.right = right;
    }
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode tmp = root.left;
    root.left = root.right;
    root.right = tmp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  private static void printPreOrder(TreeNode node) {
    if (node == null) {
      return;
    }
    System.out.print(node.val + " ");
    printPreOrder(node.left);
    printPreOrder(node.right);
  }

  public static void main(String[] args) {
    InvertBinaryTree solution = new InvertBinaryTree();
    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(9);

    TreeNode invertedRoot = solution.invertTree(root);
    // You can add code here to print the tree in order to verify the inversion
    // print tree as an array with pre-order
    printPreOrder(invertedRoot);
  }
}
