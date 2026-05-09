
public class SingleNumber {

  public int singleNumber(int[] nums) {
    var result = 0;
    for (int i = 0; i < nums.length; i++) {
      result ^= nums[i];
    }
    return result;
  }

  public static void main(String[] args) {
    int[] arr = { 4, 1, 2, 1, 2 };
    SingleNumber sol = new SingleNumber();
    System.out.println(sol.singleNumber(arr)); // Output: 4
  }

}
