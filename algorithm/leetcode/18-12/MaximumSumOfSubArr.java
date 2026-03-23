
public class MaximumSumOfSubArr {
  public int maxSum(int[] arr, int k) {
    if (arr.length < k) {
      return 0;
    }

    int result = Integer.MIN_VALUE;
    int temp = 0;
    for (int i = 0; i <= arr.length - k; i++) {
      // if first loop count sum first
      if (i == 0) {
        for (int j = 0; j < k; j++) {
          temp += arr[j];
        }

        result = Math.max(result, temp);
        continue;
      }

      temp += arr[i + k - 1];
      temp -= arr[i - 1];
      result = Math.max(result, temp);
    }

    return result;
  }

  public static void main(String[] args) {
    MaximumSumOfSubArr obj = new MaximumSumOfSubArr();
    int[] arr = { 5, 2, -1, 0, 3 };
    int k = 3;
    System.out.println(obj.maxSum(arr, k));
  }
}
