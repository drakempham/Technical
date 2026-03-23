
public class SameBinaryTree {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }

    if (p == null || q == null || p.val != q.val) {
      return false;
    }

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  public static void main(String[] args) {
    SameBinaryTree solution = new SameBinaryTree();
    TreeNode p = new TreeNode(1);
    p.left = new TreeNode(2);
    p.right = new TreeNode(3);

    TreeNode q = new TreeNode(1);
    q.left = new TreeNode(2);
    q.right = new TreeNode(3);

    boolean result = solution.isSameTree(p, q);
    System.out.println("Are the two trees the same? " + result); // Output: true

    TreeNode p1 = new TreeNode(1);
    p1.left = new TreeNode(2);
    p1.right = new TreeNode(1);
    TreeNode q1 = new TreeNode(1);
    q1.left = new TreeNode(1);
    q1.right = new TreeNode(2);
    boolean result1 = solution.isSameTree(p1, q1);
    System.out.println("Are the two trees the same? " + result1); // Output: false
  }
}
