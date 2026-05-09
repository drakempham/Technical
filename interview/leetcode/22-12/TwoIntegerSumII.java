public class TwoIntegerSumII {
  // sorting moi dung kieu nay, ko thi phai dung hashMap thoi
  public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum == target) {
        return new int[] { left + 1, right + 1 };
      } else if (sum < target) {
        left += 1;
      } else {
        right -= 1;
      }
    }

    return new int[] { -1, -1 };
  }

  public static void main(String[] args) {
    TwoIntegerSumII solution = new TwoIntegerSumII();
    int[] numbers = { 2, 7, 11, 15 };
    int target = 9;
    int[] result = solution.twoSum(numbers, target);
    System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
  }
}
