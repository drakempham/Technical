package day28012026;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

// Notice that the solution set must not contain duplicate triplets.

public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums.length < 3) {
      return new ArrayList<>();
    }

    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0) {
        break;
      }
      // should continue , never skip
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int sum = -nums[i];
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {

        if (nums[left] + nums[right] == sum) {
          result.add(List.of(nums[i], nums[left], nums[right]));
          left++;
          right--;
          while (left < right && nums[left] == nums[left - 1]) {
            left++;
          }
          while (left < right && nums[right] == nums[right + 1]) {
            right--;
          }
        } else if (nums[left] + nums[right] < sum) {
          left++;
        } else {
          right--;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    ThreeSum solution = new ThreeSum();
    // System.out.println(solution.threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
    System.out.println(solution.threeSum(new int[] { 2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4, 5, 5, -10 }));
  }
}
