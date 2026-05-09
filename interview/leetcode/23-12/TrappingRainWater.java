public class TrappingRainWater {
  public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];
    int totalWaterTrapped = 0;

    // find the highest length in the left of each position
    for (int i = 0; i < height.length; i++) {
      if (i == 0) {
        leftMax[i] = 0;
      } else {
        leftMax[i] = Math.max(leftMax[i - 1], height[i]);
      }
    }

    for (int i = height.length - 1; i >= 0; i--) {
      if (i == height.length - 1) {
        rightMax[i] = 0;
      }
    }

    return totalWaterTrapped;
  }
}
