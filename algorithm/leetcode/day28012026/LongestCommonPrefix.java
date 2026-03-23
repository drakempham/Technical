package day28012026;

public class LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }

    var prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }
    return prefix;
  }

  public static void main(String[] args) {
    var solution = new LongestCommonPrefix();
    System.out.println(solution.longestCommonPrefix(new String[] { "flower", "flow", "flight" })); // Output: "fl"
  }
}
