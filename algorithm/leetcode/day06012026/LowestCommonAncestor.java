
import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // The naive way: Path comparison
  public TreeNode lowestCommonAncestorPathComparison(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pathP = new ArrayList<>();
    List<TreeNode> pathQ = new ArrayList<>();

    getPath(pathP, root, p);
    getPath(pathQ, root, q);

    int idx = 0;
    TreeNode res = new TreeNode(0);
    while (idx < pathP.size() && idx < pathQ.size() && pathP.get(idx) == pathQ.get(idx)) {
      res = pathP.get(idx);
      idx++;
    }

    return res;
  }

  public boolean getPath(List<TreeNode> path, TreeNode root, TreeNode p) {
    if (root == null) {
      return false;
    }

    // add node
    path.add(root);

    if (root == p) {
      return true;
    }

    // BST so we find whether go left or right
    if (root.val > p.val) {
      if (getPath(path, root.left, p)) {
        return true;
      }
    } else {
      if (getPath(path, root.right, p)) {
        return true;
      }
    }

    // not find tree, so we remove it
    path.remove(path.size() - 1);
    return false;
  }

  // if p and q are on different side of root, then root is LCA
  // if both p and q are on left side, then LCA is on left side
  // if both p and q are on right side, then LCA is on right side
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode curr = root;

    while (curr != null) {
      if (p.val < curr.val && q.val < curr.val) {
        curr = curr.left;
      } else if (p.val > curr.val && q.val > curr.val) {
        curr = curr.right;
      } else {
        return curr;
      }
    }

    return null;
  }

  public static void main(String[] args) {
    LowestCommonAncestor solu = new LowestCommonAncestor();
    TreeNode root = new TreeNode(6);
    root.left = new TreeNode(2);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(0);
    root.left.right = new TreeNode(4);
    root.left.right.left = new TreeNode(3);
    root.left.right.right = new TreeNode(5);
    root.right.left = new TreeNode(7);
    root.right.right = new TreeNode(9);

    TreeNode res = solu.lowestCommonAncestor(root, root.left, root.right);
    System.out.println(res.val); // 6
  }
}
