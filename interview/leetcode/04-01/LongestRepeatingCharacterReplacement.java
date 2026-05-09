// The native of problem is to find the longest existence of a character can be
// replace the other characters within k replacement so the formula is: (right - left + 1) - maxCount <=k
// don't need to maxCount for each range (left, right) window, cause the maxCount larger, the more thinner sliding window.
public class LongestRepeatingCharacterReplacement {
  public int characterReplacement(String s, int k) {
    int res = 0, left = 0;
    int maxFreq = 0; // the max frequency of a single character in the current window
    int[] count = new int[26];
    for (int right = 0; right < s.length(); right++) {
      count[s.charAt(right) - 'A']++;
      maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

      while ((right - left + 1) - maxFreq > k) { // replace max k characters
        count[s.charAt(left) - 'A']--;
        left++;
      }

      res = Math.max(res, right - left + 1);
    }

    return res;
  }

  public static void main(String[] args) {
    LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
    String s = "AABABBA";
    int k = 1;
    System.out.println(solution.characterReplacement(s, k)); // Output: 4
  }
}
