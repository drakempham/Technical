package day28012026;

import java.util.Arrays;

public class ThreeSumClosest {
  public int threeSumClosest(int[] nums, int target) {
    if (nums.length < 3) {
      return -1;
    }
    Arrays.sort(nums);
    int closestSum = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < nums.length - 2; i++) {
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum == target) {
          return sum;
        }
        long closestDiff = (long) Math.abs(closestSum - target);
        long currentDiff = (long) Math.abs(sum - target);

        if (currentDiff < closestDiff) {
          closestSum = sum;
        }
        if (sum < target) {
          left++;
        } else {
          right--;
        }
      }
    }
    return closestSum;
  }

  public static void main(String[] args) {
    ThreeSumClosest solution = new ThreeSumClosest();
    // int[] nums = { -1, 2, 1, -4 };
    // int target = 1;
    // System.out.println(solution.threeSumClosest(nums, target));

    int[] nums2 = { 7, 8, 9 };
    int target2 = -1;
    System.out.println(solution.threeSumClosest(nums2, target2));
  }
}
