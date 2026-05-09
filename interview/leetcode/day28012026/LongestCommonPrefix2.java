package day28012026;

public class LongestCommonPrefix2 {
  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }

    String prefix = strs[0];
    for (String str : strs) {
      while (str.indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.equals("")) {
          return prefix;
        }
      }
    }

    return prefix;
  }

  public static void main(String[] args) {
    LongestCommonPrefix2 solution = new LongestCommonPrefix2();
    System.out.println(solution.longestCommonPrefix(new String[] { "flower", "flow", "flight" }));
  }
}
