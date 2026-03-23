import java.util.Arrays;

public class LongestCommonPregix {
  public String longestCommonPrefix(String[] strs) {
    Arrays.sort(strs);
    String first = strs[0];
    String last = strs[strs.length - 1];
    for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
      if (first.charAt(i) != last.charAt(i)) {
        return first.substring(0, i);
      }
    }

    return first;
  }

  public static void main(String[] args) {
    LongestCommonPregix solution = new LongestCommonPregix();
    String[] strs = { "flower", "flow", "flight" };
    String result = solution.longestCommonPrefix(strs);
    System.out.println(result); // Output: "fl"
  }
}
