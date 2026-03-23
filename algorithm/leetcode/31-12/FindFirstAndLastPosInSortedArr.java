public class FindFirstAndLastPosInSortedArr {
  public int[] searchRange(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int[] result = new int[] { -1, -1 };
    // find first position
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    // not found any ele -> return
    if (left == nums.length || nums[left] != target) {
      return result;
    }

    result[0] = left;
    left = 0;
    right = nums.length - 1;

    // find last element
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    result[1] = right;

    return result;
  }

  public static void main(String[] args) {
    FindFirstAndLastPosInSortedArr solution = new FindFirstAndLastPosInSortedArr();
    int[] nums = { 5, 7, 7, 8, 8, 10 };
    int target = 8;
    int[] result = solution.searchRange(nums, target);
    System.out.println("First and last positions of target " + target + ": [" + result[0] + ", " + result[1] + "]"); // Output:
                                                                                              [3,
                                                                                                                     // 4]
  }
}
