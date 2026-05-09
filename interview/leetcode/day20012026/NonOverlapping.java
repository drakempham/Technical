import java.util.Arrays;

public class NonOverlapping {
  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0)
      return 0;
    int result = 0;
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
    int lastEnd = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
      int currStart = intervals[i][0];

      // just continue the loop
      if (currStart < lastEnd) {
        result += 1;
      } else {
        lastEnd = intervals[i][1];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    NonOverlapping nonOverlapping = new NonOverlapping();
    // int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
    int[][] intervals = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};
    int result = nonOverlapping.eraseOverlapIntervals(intervals);
    System.out.println(result);
  }
}
