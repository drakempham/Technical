
public class IsBst {
  static class Node {
    int data;
    Node left, right;

    Node(int item) {
      this.data = item;
      this.left = this.right = null;
    }
  }

  boolean isBst(Node node) {
    return isBstUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  boolean isBstUtil(Node node, int min, int max) {
    if (node == null) {
      return true;
    }

    if (node.data < min || node.data > max) {
      return false;
    }

    return isBstUtil(node.left, min, node.data - 1) && isBstUtil(node.right, node.data + 1, max);
  }

  public static void main(String[] args) {
    IsBst tree = new IsBst();
    IsBst.Node root = new IsBst.Node(4);
    root.left = new IsBst.Node(2);
    root.right = new IsBst.Node(5);
    root.left.left = new IsBst.Node(1);
    root.left.right = new IsBst.Node(3);

    if (tree.isBst(root)) {
      System.out.println("Đây là cây BST");
    } else {
      System.out.println("Đây không phải là cây BST");
    }
  }
}
