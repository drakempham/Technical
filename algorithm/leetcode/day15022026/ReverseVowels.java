package day15022026;

import java.util.Set;

public class ReverseVowels {

  // swap string using char[]
  // using full set instead of using toLowerCase
  private Set<Character> set = Set.of(
      'u', 'e', 'o', 'a', 'i',
      'U', 'E', 'O', 'A', 'I');

  public String reverseVowels(String s) {
    if (s == null || s.length() == 1) {
      return s;
    }

    char[] ch = s.toCharArray();
    int left = 0, right = s.length() - 1;
    while (left < right) {
      while (left < right && !set.contains(ch[left]))
        left++;
      while (left < right && !set.contains(ch[right]))
        right--;
      if (left < right) {
        char temp = ch[left];
        ch[left++] = ch[right];
        ch[right--] = temp;
      }
    }

    return new String(ch);
  }

  public static void main(String[] args) {
    ReverseVowels sol = new ReverseVowels();
    System.out.println(sol.reverseVowels("acECreIm"));
  }
}
