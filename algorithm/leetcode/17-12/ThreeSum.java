import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
  // if nums[0] + nums[1] + nums[2] = 0 -> - nums[0] = nums[1] + nums[2]
  // need to be careful duplicate in two place, the sum element and the left and
  // right pointers
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 3) {
      return result;
    }

    // O(nlogn)
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      // skip duplicate
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int sum = -nums[i];
      int left = i + 1;
      int right = nums.length - 1;

      while (left < right) {
        int currSum = nums[left] + nums[right];
        if (currSum == sum) {
          // fixed-size list
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));

          // Skip duplicate for the pointer
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }

          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }

          left++;
          right--;
        } else if (currSum < sum) {
          left++;
        } else {
          right--;
        }
      }
    }

    return result;

  }

  public static void main(String[] args) {
    ThreeSum obj = new ThreeSum();
    int[] nums = { -1, 0, 1, 2, -1, -4 };
    System.out.println(obj.threeSum(nums));
  }
}
