
import java.util.HashSet;

public class LongestConsecutiveSequence {
  // 3 ways
  // First is using brute force, O(n3) - check each element, check consecutive,
  // check if contains
  // 2nd is using sorting, O(nlogn) - sort the array, then check consecutive
  // 3rd is using HashSet, O(n) - add all elements to hashset - the problem is to
  // check occurance, not consecutive

  // ways 3
  public int longestConsecutiveSequence(int[] nums) {
    var hashSet = new HashSet<Integer>();
    for (int ele : nums) {
      hashSet.add(ele);
    }

    var result = 0;
    for (int ele : nums) {
      // instead of check elements, only check the longest array,
      if (hashSet.contains(ele - 1)) {
        continue;
      }

      var startPos = ele;
      while (hashSet.contains(startPos + 1)) {
        startPos = startPos + 1;
      }

      result = Math.max(result, startPos - ele + 1);
    }

    return result;
  }

  public static void main(String[] args) {
    LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
    int[] nums = { 100, 4, 200, 1, 3, 2 };
    int result = solution.longestConsecutiveSequence(nums);
    System.out.println("Longest consecutive sequence length: " + result);
  }
}
