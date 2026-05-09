public class RemoveElements {
  public int removeElement(int[] nums, int val) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[count] = nums[i];
        count++;
      }
    }

    return count;
  }

  public static void main(String[] args) {
    RemoveElements solution = new RemoveElements();
    int[] nums = { 3, 2, 2, 3 };
    int val = 3;
    int newLength = solution.removeElement(nums, val);
    System.out.println("New length: " + newLength); // Output: New length: 2
  }
}
