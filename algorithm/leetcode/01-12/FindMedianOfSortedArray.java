class Solution {
   public int removeDuplicates(int[] nums) {
      if (nums.length == 0) {
         return 0;
      }

      int count = 0;
      for (int i = 0; i < nums.length; i++) {
         if (i == 0) {
            count += 1;
            continue;
         }

         if (nums[i] != nums[i - 1]) {
            nums[count] = nums[i];
            count += 1;
         }

      }

      return count;
   }

   public static void main(String[] args) {
      // var nums = new int[] {1,1,2};
      // var sol = new Solution();
      // var result = sol.removeDuplicates(nums);
      // System.out.println(result); // Output: 2
      // System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums,
      // result))); // Output: [1,2]

      var nums2 = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
      var sol = new Solution();
      var result2 = sol.removeDuplicates(nums2);
      System.out.println(result2); // Output: 5
      System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOf(nums2, result2))); // Output: [0,1,2,3,4]
   }
}
