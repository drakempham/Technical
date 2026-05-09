package day20012026;

public class MaxJump {
  public boolean canJump(int[] nums) {
    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > maxReach) {
        return false;
      }

      maxReach = Math.max(maxReach, i + nums[i]);
    }

    return maxReach >= nums.length - 1;
  }

  public static void main(String[] args) {
    MaxJump maxJump = new MaxJump();
    int[] nums = { 2, 3, 1, 1, 4 };
    boolean result = maxJump.canJump(nums);
    System.out.println(result);
  }
}
