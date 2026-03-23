package day26022026;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalI {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // reverse recursive
  public static <T> List<T> reverseRecursive(List<T> list) {
    if (list.size() <= 1)
      return list;

    List<T> reverse = new ArrayList<>();
    reverse.add(list.get(list.size() - 1));
    reverse.addAll(reverseRecursive(list.subList(0, list.size() - 1)));
    return reverse;
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    List<List<Integer>> result = new ArrayList<>();
    while (!queue.isEmpty()) {
      int n = queue.size();
      List<Integer> ele = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        TreeNode node = queue.poll();
        ele.add(node.val);
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }

      result.add(ele);

    }

    // reverse the ele

    Collections.reverse(result);

    return result;
  }

  public List<List<Integer>> levelOrderBottom2(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    LinkedList<List<Integer>> result = new LinkedList<>();
    while (!queue.isEmpty()) {
      int n = queue.size();
      List<Integer> ele = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        TreeNode node = queue.poll();
        ele.add(node.val);
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }

      result.addFirst(ele);

    }

    return result;
  }

  public static void main(String[] args) {
    BinaryTreeLevelOrderTraversalI sol = new BinaryTreeLevelOrderTraversalI();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    System.out.println(sol.levelOrderBottom2(root));
  }
}
