package day20012026;

public class MaximumSubArr {
  public int maxSubArray(int[] nums) {
    int result = Integer.MIN_VALUE;
    int currSum = 0;
    // loop through array and cutimulate the sum of continuous value, if the currSUm
    // < 0 mean we need to begin again
    for (int num : nums) {
      currSum = currSum < 0 ? 0 : currSum;
      currSum += num;
      result = Math.max(result, currSum);
    }

    return result;
  }

  public static void main(String[] args) {
    MaximumSubArr maximumSubArr = new MaximumSubArr();
    int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
    int result = maximumSubArr.maxSubArray(nums);
    System.out.println(result);
  }
}
