
import java.util.ArrayList;
import java.util.List;

public class IsPalindrome {
  public List<List<String>> isPalindrome(String s) {
    List<List<String>> result = new ArrayList<>();
    backtrack(result, s, new ArrayList<>(), 0);
    return result;
  }

  public void backtrack(List<List<String>> result, String s, List<String> temp, int start) {
    if (start == s.length()) {
      result.add(new ArrayList<>(temp));
    }

    for (int end = start; end < s.length(); end++) {
      if (isPalindrome(s, start, end)) {
        temp.add(s.substring(start, end + 1));
        backtrack(result, s, temp, end + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  public boolean isPalindrome(String s, int start, int end) {
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }

    return true;
  }

  public static void main(String[] args) {
    IsPalindrome isPalindrome = new IsPalindrome();
    String s = "aab";
    System.out.println(isPalindrome.isPalindrome(s));
    List<List<String>> result = isPalindrome.isPalindrome(s);
    for (List<String> list : result) {
      System.out.println(list);
    }
  }
}
