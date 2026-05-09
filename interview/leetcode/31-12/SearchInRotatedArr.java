public class SearchInRotatedArr {
  public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      // always one side is sorted -> find which side is sorted , if not sorted,
      // search in the other side
      // you can choose check mid with left or right
      if (nums[mid] <= nums[right]) {
        // target in the right side
        if (target >= nums[mid] && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
        // nums [mid] > nums[right] -> swap
      } else {
        if (target >= nums[left] && target <= nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    SearchInRotatedArr solution = new SearchInRotatedArr();
    // int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
    // int target = 0;
    // int result = solution.search(nums, target);
    // System.out.println("Index of target " + target + ": " + result); // Output: 4

    int[] nums2 = { 5, 1, 3 };
    int target2 = 5;
    int result2 = solution.search(nums2, target2);
    System.out.println("Index of target " + target2 + ": " + result2); // Output: 0
  }
}