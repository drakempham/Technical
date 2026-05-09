package day23022026;

public class ArithmeticSlices {

  // find maxLen
  // public int numberOfArithmeticSlices(int[] nums) {
  // int left = 0, right = 1;
  // int maxLen = 0;
  // while (right < nums.length) {
  // while ((nums[right] - nums[left]) / (right - left) != nums[right] -
  // nums[right - 1]) {
  // left++;
  // }

  // if (right - left >= 2) {
  // maxLen = Math.max(maxLen, right - left + 1);
  // }

  // right++;
  // }

  // return maxLen;
  // }

  // when add new elements to the array, L-1 subarray with length >=3 will be
  // created
  public int numberOfArithmeticSlices(int[] nums) {
    if (nums.length < 3) {
      return 0;
    }

    int left = 0, right = 2;
    int total = 0;
    while (right < nums.length) {
      if (nums[right] - nums[right - 1] == nums[right - 1] - nums[right - 2]) {
        // left -> right -1 are aritmetic and counted in total
        // total = (right - 1 - left +1) -1 = right - left - 1
        total += (right - left - 1);
      } else {
        left = right - 1;
      }

      right++;
    }

    return total;
  }

  public static void main(String[] args) {
    ArithmeticSlices sol = new ArithmeticSlices();
    System.out.println(sol.numberOfArithmeticSlices(new int[] { 1, 2, 3, 4 })); // [1,2,3] , [2,3,4], [1,2,3,4]
    System.out.println(sol.numberOfArithmeticSlices(new int[] { 1 }));

  }
}
