
public class BinarySearch {
  public int search(int[] nums, int target) { // nums is sorted
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    BinarySearch solution = new BinarySearch();
    int[] nums = { -1, 0, 3, 5, 9, 12 };
    int target = 9;
    int result = solution.search(nums, target);
    System.out.println("Index of target " + target + ": " + result); // Output: 4
  }
}
