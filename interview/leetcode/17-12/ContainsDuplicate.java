import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {
  // O(n log n) time, O(1) space
  public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        return true;
      }
    }

    return false;
  }

  // O(n) time, O(n) space
  public boolean containsDuplicate2(int[] nums) {
    var set = new HashSet<Integer>();

    for (int i = 0; i < nums.length; i++) {
      if (!set.add(nums[i])) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    ContainsDuplicate cd = new ContainsDuplicate();

    int[] nums = { 1, 2, 3, 1 };
    boolean result = cd.containsDuplicate(nums);
    System.out.println(result);

    int[] nums2 = { 1, 2, 3, 1 };
    boolean result2 = cd.containsDuplicate(nums2);
    System.out.println(result2);
  }
}
