import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // recursive
  public int maxDepthRecursive(TreeNode root) {
    if (root == null)
      return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  // this can cause overflow caused if tree is large, no need store frame stack
  // we can use BFS ( no recursive)
  public int maxDepth(TreeNode root) {
    if (root == null)
      return 0;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int depth = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      depth++;

      while (size > 0) {
        TreeNode node = queue.poll();
        queue.offer(node.left);
        queue.offer(node.right);
      }
    }

    return depth;
  }

  public static void main(String[] args) {
    MaxDepth solution = new MaxDepth();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    int depth = solution.maxDepth(root);
    System.out.println("Max Depth: " + depth); // Output: 3
  }
}
