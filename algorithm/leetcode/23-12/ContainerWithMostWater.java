public class ContainerWithMostWater {
  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
      int width = right - left;
      int currHeight = Math.min(height[left], height[right]);
      int currArea = width * currHeight;
      maxArea = Math.max(maxArea, currArea);

      // Move the pointer with higher height
      if (height[left] < height[right]) {
        right--;
      } else {
        left++;
      }
    }

    return maxArea;
  }

  public static void main(String[] args) {
    ContainerWithMostWater sol = new ContainerWithMostWater();
    int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
    System.out.println(sol.maxArea(height)); // Output: 49
  }
}
