package day30012026;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0)
      return 0;

    int i = 0; // The index of the last unique element found
    for (int j = 1; j < nums.length; j++) {
      // If we find a new unique value
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j]; // Move it to the next available slot
      }
    }

    // Return the length (index + 1)
    return i + 1;
  }

  public static void main(String[] args) {
    RemoveDuplicates solution = new RemoveDuplicates();
    int[] nums = new int[] { 1, 1, 2 };
    System.out.println(solution.removeDuplicates(nums));
    for (int num : nums) {
      System.out.print(num + " ");
    }
    int[] nums2 = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
    System.out.println(solution.removeDuplicates(nums2));
    for (int num : nums2) {
      System.out.print(num + " ");
    }
  }
}
