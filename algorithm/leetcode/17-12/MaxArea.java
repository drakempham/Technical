public class MaxArea {
  public int maxArea(int[] height) {
    if (height.length < 2) {
      return 0;
    }

    int result = 0, i = 0, j = height.length - 1;
    while (i < j) {
      result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
      if (height[i] < height[j]) {
        i = i + 1;
      } else {
        j = j - 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    MaxArea sol = new MaxArea();
    int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
    var result = sol.maxArea(height);
    System.out.println(result);
  }
}
