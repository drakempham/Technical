package day21012026;

public class JumpGameII {
  public int jump(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }
    int jumpCount = 0;
    int farthest = 0; // maximumum of jump
    int currEnd = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      farthest = Math.max(farthest, i + nums[i]);
      if (i == currEnd) {
        jumpCount += 1;
        currEnd = farthest;
        if (currEnd >= nums.length - 1)
          break;
      }
    }

    return jumpCount;
  }

  public static void main(String[] args) {
    JumpGameII solution = new JumpGameII();
    // System.out.println(solution.jump(new int[] { 2, 4, 1, 1, 1, 1 }));
    System.out.println(solution.jump(new int[] { 1, 1, 1, 1 }));
  }
}