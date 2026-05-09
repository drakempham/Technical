package day19022026;

import java.util.Arrays;

public class AssignCookie {
  // Time complexity : O(mlogm) + O(nlogn)
  // Space complexity: O(1)
  // largest to smallest
  public int findContentChildren(int[] g, int[] s) {
    if (g == null || g.length == 0) {
      return 0;
    }
    Arrays.sort(g);
    Arrays.sort(s);

    int gIdx = g.length - 1, sIdx = s.length - 1;
    int count = 0;
    while (gIdx >= 0 && sIdx >= 0) {
      if (s[sIdx] >= g[gIdx]) {
        count++;
        sIdx--;
        gIdx--;
      } else {
        gIdx--;
      }
    }

    return count;
  }

  // smallest to largest
  public int findContentChildren2(int[] g, int[] s) {
    if (g == null || g.length == 0) {
      return 0;
    }
    Arrays.sort(g);
    Arrays.sort(s);

    int child = 0;
    for (int cookie = 0; cookie < s.length && child < g.length; cookie++) {
      if (s[cookie] >= g[child]) {
        child++;
      }
    }

    return child;
  }

  public static void main(String[] args) {
    AssignCookie solution = new AssignCookie();
    int[] g = { 1, 2, 3 };
    int[] s = { 1, 1 };
    System.out.println(solution.findContentChildren(g, s));
    System.out.println(solution.findContentChildren2(g, s));

    int[] g2 = { 1, 2 };
    int[] s2 = { 1, 2, 3 };
    System.out.println(solution.findContentChildren(g2, s2));
    System.out.println(solution.findContentChildren2(g2, s2));
  }
}
