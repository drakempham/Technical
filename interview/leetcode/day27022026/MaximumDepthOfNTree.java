package day27022026;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthOfNTree {
  static class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  };

  public int maxDepth(Node root) {
    if (root == null) {
      return 0;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    int count = 0;
    while (!queue.isEmpty()) {
      int n = queue.size();
      for (int idx = 0; idx < n; idx++) {
        Node node = queue.poll();
        if (node.children != null && node.children.size() != 0) {
          for (Node child : node.children) {
            queue.add(child);
          }
        }
      }
      count++;
    }
    return count;
  }

  // implement with DFS ?

  public static void main(String[] args) {
    MaximumDepthOfNTree sol = new MaximumDepthOfNTree();
    Node root = new Node(1);
    root.children = new ArrayList<>();
    root.children.add(new Node(2));
    root.children.add(new Node(3));
    root.children.add(new Node(4));
    System.out.println(sol.maxDepth(root));
  }
}
