public class IsSubTree {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public boolean isSubTree(TreeNode s, TreeNode t) {
    if (s == null) {
      return false;
    }

    // Check if current node matches, OR recursively check left/right subtrees
    return isSameTree(s, t) || isSubTree(s.left, t) || isSubTree(s.right, t);
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
    IsSubTree solution = new IsSubTree();
    TreeNode s = new TreeNode(3);
    s.left = new TreeNode(4);
    s.right = new TreeNode(5);
    s.left.left = new TreeNode(1);
    s.left.right = new TreeNode(2);

    // Test 1: t is at s.left
    TreeNode t1 = new TreeNode(4);
    t1.left = new TreeNode(1);
    t1.right = new TreeNode(2);
    System.out.println("Test 1 - t at s.left: " + solution.isSubTree(s, t1)); // true

    // Test 2: t is at s.left.left (deeper!)
    TreeNode t2 = new TreeNode(1);
    System.out.println("Test 2 - t at s.left.left: " + solution.isSubTree(s, t2)); // true

    // Test 3: t is at s.left.right (deeper!)
    TreeNode t3 = new TreeNode(2);
    System.out.println("Test 3 - t at s.left.right: " + solution.isSubTree(s, t3)); // true

    // Test 4: t doesn't exist
    TreeNode t4 = new TreeNode(99);
    System.out.println("Test 4 - t not found: " + solution.isSubTree(s, t4)); // false
  }
}
