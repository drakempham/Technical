public class MaximumSubArray {
  // the native of this problem is to find the local maximum at each step which
  // means at each step you decide
  // whether to add the current number to the previous cumulative sum or start a
  // new sub array
  public int maxSubArray(int[] nums) {
    int res = Integer.MIN_VALUE; // global maximum
    int currMax = 0; // culmulative the current maximum sub array - can't use only this cause it
                     // start at 0
    // to sum all subArray, whether the result must be start at Min_VALUE
    for (int i = 0; i < nums.length; i++) {
      currMax = Math.max(nums[i], currMax + nums[i]);
      res = Math.max(currMax, res);
    }

    return res;
  }

  public static void main(String[] args) {
    MaximumSubArray solution = new MaximumSubArray();
    int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
    System.out.println(solution.maxSubArray(nums)); // Output: 6
  }
}
