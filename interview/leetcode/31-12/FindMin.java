public class FindMin {
  public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;

    // the first element is the smallest one
    if (nums[left] < nums[right]) {
      return nums[left];
    }

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      } else {
        right = mid;
      }

    }

    return nums[right]; // or nums[left]
  }

  public static void main(String[] args) {
    FindMin solution = new FindMin();
    int[] nums = { 3, 4, 5, 1, 2 };
    int result = solution.findMin(nums);
    System.out.println("Minimum element in the rotated sorted array: " + result); // Output: 1
  }
}
