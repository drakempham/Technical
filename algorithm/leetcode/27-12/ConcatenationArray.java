class ConcatenationArray {
  // O(n) time complexity and O(2*n) space complexity
  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] result = new int[2 * n];

    for (int i = 0; i < n; i++) {
      result[i] = nums[i];
      result[i + n] = nums[i];
    }

    return result;
  }

  // native method
  public int[] getConcatenation2(int[] nums) {
    int n = nums.length;
    int[] result = new int[2 * n];

    for (int i = 0; i < n; i++) {
      System.arraycopy(nums, 0, result, 0, n);
      System.arraycopy(nums, 0, result, n, n);
    }

    return result;
  }

  public static void main(String[] args) {
    ConcatenationArray solution = new ConcatenationArray();
    int[] nums = { 1, 2, 3 };
    int[] concatenated = solution.getConcatenation(nums);

    // Print the result
    for (int num : concatenated) {
      System.out.print(num + " ");
    }
    // Expected output: 1 2 3 1 2 3
  }
}