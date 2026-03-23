import java.util.Stack;

public class LargestRectangleArea {
  public int largestRectangleArea(int[] heights) {
    // this stack store ascending elements
    Stack<Integer> pos = new Stack<>();
    int maxArea = 0;
    int n = heights.length;
    for (int i = 0; i <= n; i++) {
      int currHeight = i == n ? -1 : heights[i];// make sure end one is lowest height so we pop up all stack

      // we calculate maxArea at pos peek (not i)
      while (!pos.isEmpty() && heights[pos.peek()] > currHeight) {
        int height = heights[pos.pop()];
        int width = 0;

        if (pos.isEmpty()) {
          width = i;
        } else {
          width = i - pos.peek() - 1;
        }
        maxArea = Math.max(maxArea, height * width); // right - left - 1 (not count left and right)
      }
      pos.push(i);
    }

    return maxArea;
  }

  public static void main(String[] args) {
    LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
    int[] heights = { 2, 1, 5, 6, 2, 3 };
    System.out.println(largestRectangleArea.largestRectangleArea(heights)); // 10
  }
}
