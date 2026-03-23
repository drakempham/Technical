package day23022026;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-harmonious-subsequence/description/?envType=problem-list-v2&envId=sliding-window
public class LongestHarmonius {
  // brute force - count and find out the maximum
  // space: O(n)
  // time: O(n)
  public int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int maxLen = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int num = entry.getKey();
      // if num is greater
      if (map.containsKey(num - 1)) {
        maxLen = Math.max(maxLen, map.getOrDefault(num, 0) + map.getOrDefault(num - 1, 0));
      }
      // if num is smaller
      if (map.containsKey(num + 1)) {
        maxLen = Math.max(maxLen, map.getOrDefault(num, 0) + map.getOrDefault(num + 1, 0));
      }
    }

    return maxLen;
  }

  // sliding windows: contiguous subarray
  // time: O(n)
  // space:O(1)
  public int findLHS2(int[] nums) {
    Arrays.sort(nums);
    int left = 0, right = 0;
    int maxLen = 0;
    while (right < nums.length) {
      while (nums[right] - nums[left] > 1) {
        left++;
      }

      // we can lose to count that position
      if (nums[right] - nums[left] == 1) {
        maxLen = Math.max(maxLen, right - left + 1);
      }

      right++;
    }

    return maxLen;
  }

  public static void main(String[] args) {
    LongestHarmonius sol = new LongestHarmonius();
    System.out.println(sol.findLHS2(new int[] { 1, 3, 2, 2, 5, 2, 3, 7 }));
    System.out.println(sol.findLHS2(new int[] { 1, 1, 1, 1 }));

  }
}
