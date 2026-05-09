public class TrappingRainWaterV2 {
  public int trap(int[] height) {
    int n = height.length;
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];

    for (int i = 0; i < height.length; i++) {
      if (i == 0) {
        leftMax[i] = height[i];
      } else {
        leftMax[i] = Math.max(leftMax[i - 1], height[i]); // water container at each position should be higher than its
                                                          // height
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      if (i == n - 1) {
        rightMax[i] = height[i];
      } else {
        rightMax[i] = Math.max(rightMax[i + 1], height[i]);
      }
    }

    int result = 0;
    for (int idx = 0; idx < n; idx++) {
      result += Math.min(leftMax[idx], rightMax[idx]) - height[idx];

    }

    return result;
  }

  public static void main(String[] args) {
    TrappingRainWaterV2 solver = new TrappingRainWaterV2();
    int[] height = { 0, 2, 0, 3, 1, 0, 1, 3, 2, 1 };
    int trappedWater = solver.trap(height);
    System.out.println("Trapped Rain Water: " + trappedWater); // Output: 9
  }

}
