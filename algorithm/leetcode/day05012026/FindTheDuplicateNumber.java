public class FindTheDuplicateNumber {
  // Idea is using negation idx to mark its existence
  // we use the array as the indexed to mark the element exist
  // cause the range value is [1->n] we map to [0->n-1] and there are n+1
  // elements. one must dup
  public int findDuplicateIndex(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int idxCheck = Math.abs(nums[i]) - 1;

      if (nums[idxCheck] < 0) {
        return Math.abs(nums[i]); // val is dup
      }

      nums[idxCheck] *= -1;
    }

    return -1;
  }

  // way2 - Floyd's Tortoise and Hare (Cycle Detection)
  public int findDuplicate(int[] nums) {
    int slow = nums[0];
    int fast = nums[nums[0]];
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[nums[fast]];
    }

    slow = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }

    return fast; // or slow
  }

  public static void main(String[] args) {
    FindTheDuplicateNumber solution = new FindTheDuplicateNumber();
    int[] nums = { 3, 1, 3, 4, 2 };
    int result = solution.findDuplicate(nums);
    System.out.println(result); // expected output: 3
  }
}
