
import java.util.HashMap;

public class Anagram {
  public boolean isAnagram(String s, String t) {
    // if the two strings are not same length, they cannot be anagrams
    if (s.length() != t.length()) {
      return false;
    }

    int[] charCount = new int[26];

    // present at s +, present at t -
    for (int i = 0; i < s.length(); i++) {
      charCount[s.charAt(i) - 'a']++;
      charCount[t.charAt(i) - 'a']--;
    }

    for (int i = 0; i < charCount.length; i++) {
      if (charCount[i] != 0) {
        return false;
      }
    }

    return true;
  }

  // use hashmap - if unicode characters are present
  public boolean isAnagram2(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    HashMap<Character, Integer> charCount = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + 1);
      charCount.put(t.charAt(i), charCount.getOrDefault(t.charAt(i), 0) - 1);
    }

    for (int count : charCount.values()) {
      if (count != 0) {
        return false;
      }
    }

    return true;
  }

  // loop through two arrays
  public boolean isAnagram3(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    HashMap<Character, Integer> charCountS = new HashMap<>();

    for (char c : s.toCharArray()) {
      charCountS.put(c, charCountS.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {
      if (charCountS.get(c) != null && charCountS.getOrDefault(c, 0) != 0) {
        charCountS.put(c, charCountS.get(c) - 1);
      } else {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    String s = "racecar", t = "carrace";
    Anagram a = new Anagram();
    System.out.println(a.isAnagram(s, t));
  }
}
