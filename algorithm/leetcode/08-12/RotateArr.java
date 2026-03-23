public class RotateArr {
  public void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }

  public void rotate(int[] nums, int k) {
    if (nums.length == 0 || nums.length == 1) {
      return;
    }

    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
    int k = 3;
    RotateArr sol = new RotateArr();
    sol.rotate(arr, k);
    for (int num : arr) {
      System.out.print(num + " ");
    }
    // Output: 5 6 7 1 2 3 4
  }
}
