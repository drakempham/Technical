public class MoveZeros {
  public void moveZeros(int[] nums) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[count] = nums[i];
        count += 1;
      }
    }
    while (count < nums.length) {
      nums[count] = 0;
      count += 1;
    }
  }

  public static void main(String[] args) {
    MoveZeros solution = new MoveZeros();
    int[] nums = { 0, 1, 0, 3, 12 };
    solution.moveZeros(nums);
    for (int num : nums) {
      System.out.print(num + " ");
    }
  }
}
