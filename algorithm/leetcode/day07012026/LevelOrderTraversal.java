import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return null;
    }

    List<List<Integer>> result = new ArrayList<>();

    bstTraversal(result, root);
    return result;
  }

  public void bstTraversal(List<List<Integer>> result, TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      List<Integer> temp = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        temp.add(node.val);
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }

      result.add(temp);
    }
  }

  // dfs approach
  public List<List<Integer>> levelOrderDfs(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, root, 0);
    return result;
  }

  public void dfs(List<List<Integer>> result, TreeNode root, int level) {
    if (root == null) {
      return;
    }

    if (level == result.size()) { // level + 1= result.size()
      result.add(new ArrayList<>());
    }

    result.get(level).add(root.val);

    dfs(result, root.left, level + 1);
    dfs(result, root.right, level + 1);
  }

  public static void main(String[] args) {
    LevelOrderTraversal solu = new LevelOrderTraversal();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    // var result = solu.levelOrder(root);
    // for (var list : result) {
    // for (var ele : list) {
    // System.out.print(ele + " ");
    // }
    // System.out.println();
    // }

    var result2 = solu.levelOrderDfs(root);
    for (var list : result2) {
      for (var ele : list) {
        System.out.print(ele + " ");
      }
      System.out.println();
    }
  }
}
