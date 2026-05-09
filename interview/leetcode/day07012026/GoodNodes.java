public class GoodNodes {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int goodNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return dfs(root, Integer.MIN_VALUE);
  }

  public int dfs(TreeNode root, int maxSoFar) {
    if (root == null) {
      return 0;
    }

    int count = 0;
    if (root.val >= maxSoFar) {
      count += 1;
      maxSoFar = root.val;
    }

    count += dfs(root.left, maxSoFar) + dfs(root.right, maxSoFar);
    return count;
  }

  public static void main(String[] args) {
    GoodNodes solu = new GoodNodes();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    root.left.left = new TreeNode(3);
    root.right.right = new TreeNode(5);
    root.right.left = new TreeNode(1);

    int result = solu.goodNodes(root);
    System.out.println("Number of good nodes: " + result); // Output: 4
  }
}
