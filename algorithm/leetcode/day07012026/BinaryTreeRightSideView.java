import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs(result, root, 0);
    return result;
  }

  public void dfs(List<Integer> result, TreeNode root, int level) {
    if (root == null) {
      return;
    }

    if (level == result.size()) {
      result.add(root.val);
    }

    dfs(result, root.right, level + 1);
    dfs(result, root.left, level + 1);
  }

  public static void main(String[] args) {
    BinaryTreeRightSideView solu = new BinaryTreeRightSideView();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.right = new TreeNode(5);
    root.right.right = new TreeNode(4);

    var result = solu.rightSideView(root);
    for (var ele : result) {
      System.out.print(ele + " ");
    }
  }
}
