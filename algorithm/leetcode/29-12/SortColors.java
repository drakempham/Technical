public class SortColors {
  // in-place algorithm (3 pointers). Complexity: O(n), space: O(1)
  // We split the array into 4 sections:
  // [0, low-1]: all 0s
  // [low, mid-1]: all 1s
  // [mid, high]: unknown
  // [high+1, n-1]: all 2s
  // We process the mid pointer until it passes the right pointer

  // cach nay dung cho dimension nho, neu dimension lon hon nen dung counting sort
  public void sortColors(int[] nums) {
    int low = 0;
    int mid = 0;
    int high = nums.length - 1;

    while (mid <= high) {
      switch (nums[mid]) {
        case 0 -> {
          // Cause we process from beginning (0 -> low) always >=0 <=1
          swap(nums, mid, low);
          mid++;
          low++;
          // low -> mid
        }
        case 1 -> mid++;
        default -> {
          swap(nums, mid, high);
          high--;
          // recheck the elements at high ( don't increase mid)
        }
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    SortColors sorter = new SortColors();
    int[] nums = { 2, 0, 2, 1, 1, 0 };
    sorter.sortColors(nums);
    for (int num : nums) {
      System.out.print(num + " ");
    }
  }
}
