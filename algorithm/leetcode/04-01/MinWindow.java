public class MinWindow {
  public String minWindow(String s, String t) {
    int[] destCount = new int[128];

    for (int i = 0; i < t.length(); i++) {
      destCount[t.charAt(i)]++;
    }

    int diffChar = 0;
    for (int i : destCount) {
      if (i != 0) {
        diffChar++;
      }
    }

    int left = 0;
    int[] sourceCount = new int[128];
    int currCount = 0;
    int minCount = Integer.MAX_VALUE;
    String res = "";

    for (int right = 0; right < s.length(); right++) {
      char currSChar = s.charAt(right);
      sourceCount[currSChar]++;

      if (destCount[currSChar] > 0 && sourceCount[currSChar] == destCount[currSChar]) {
        currCount++;
      }

      while (currCount == diffChar) { // try sorter
        if (right - left + 1 < minCount) {
          minCount = right - left + 1;
          res = s.substring(left, right + 1);
        }

        if (destCount[s.charAt(left)] > 0 && sourceCount[s.charAt(left)] == destCount[s.charAt(left)]) {
          currCount--;
        }
        sourceCount[s.charAt(left)]--;
        left++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    MinWindow solution = new MinWindow();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution.minWindow(s, t)); // Output: "BANC"
  }
}
