// to check if two string is permulation of each other, sort it and compare
// or count the frequency of each character and compare
public class PermutationInString {
  public boolean checkInclusion(String s1, String s2) {
    int[] freqCounts1 = new int[26];
    int[] freqCounts2 = new int[26];

    if (s1.length() > s2.length()) {
      return false;
    }

    for (int i = 0; i < s1.length(); i++) {
      freqCounts1[s1.charAt(i) - 'a']++;
      freqCounts2[s2.charAt(i) - 'a']++;
    }

    int matches = 0;
    for (int i = 0; i < 26; i++) {
      if (freqCounts1[i] == freqCounts2[i]) {
        matches++;
      }
    }

    for (int right = s1.length(); right < s2.length(); right++) {
      // instead of checking freqMatching at each step, we can keep a count of
      // matching
      if (matches == 26) {
        return true;
      }

      int l = s2.charAt(right - s1.length()) - 'a';
      int r = s2.charAt(right) - 'a';

      freqCounts2[r]++;
      if (freqCounts2[r] == freqCounts1[r]) {
        matches++;
      } else if (freqCounts2[r] == freqCounts1[r] + 1) {
        matches--;
      }

      freqCounts2[l]--;
      if (freqCounts1[l] == freqCounts2[l]) {
        matches++;
      } else if (freqCounts1[l] - 1 == freqCounts2[l]) {
        matches--;
      }
    }

    return matches == 26;
  }

  public static void main(String[] args) {
    PermutationInString solution = new PermutationInString();
    String s1 = "abc";
    String s2 = "bbbca";
    System.out.println(solution.checkInclusion(s1, s2)); // Output: true
  }
}
