import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStrWithoutDuplicate {
  // public int lengthOfLongestSubString(String s) {
  // if (s.length() == 0) {
  // return 0;
  // }
  // int left = 0;
  // int right = 1;
  // int result = 1;

  // while (right < s.length()) {
  // while (right < s.length() && s[right] == s[right + 1]) {
  // right++;
  // }

  // while (left < right && s[left] == s[right]) {

  // }
  // }
  // }

  // cách co dien
  public int lengthOfLongestSubStringSet(String s) {
    Set<Character> exists = new HashSet<>();
    int maxLen = 0, left = 0, right = 0;

    while (right < s.length()) {
      if (!exists.contains(s.charAt(right))) {
        exists.add(s.charAt(right));
        maxLen = Math.max(maxLen, right - left + 1);
        right++;
      } else {
        exists.remove(s.charAt(left));
        left++;
      }
    }

    return maxLen;
  }

  public int lengthOfLongestSubStringMap(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int maxLen = 0, left = 0, right = 0;

    while (right < s.length()) {
      left = Math.max(left, map.getOrDefault(s.charAt(right), 0));
      maxLen = Math.max(maxLen, right - left + 1);
      map.put(s.charAt(right), right + 1);
      right++;
    }

    return maxLen;
  }

  public static void main(String[] args) {
    LongestSubStrWithoutDuplicate solution = new LongestSubStrWithoutDuplicate();
    String s = "abcabcbb";
    // System.out.println(solution.lengthOfLongestSubStringSet(s)); // Output: 3

    System.out.println(solution.lengthOfLongestSubStringMap(s)); // Output: 3
  }
}
