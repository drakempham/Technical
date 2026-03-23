
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    // check array length
    if (intervals.length == 0) {
      return new int[0][];
    }

    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    List<int[]> results = new ArrayList<>();
    int currEnd = intervals[0][1];
    int currStart = intervals[0][0];

    for (int i = 1; i < intervals.length; i++) {
      int nextStart = intervals[i][0];
      int nextEnd = intervals[i][1];
      if (nextStart <= currEnd) {
        currEnd = Math.max(currEnd, nextEnd);
        // add the previous one -> always missing the last on
      } else {
        results.add(new int[] {currStart, currEnd});
        currStart = nextStart;
        currEnd = nextEnd;
      }
    }

    // add the remaining caused its the largest
    results.add(new int[] {currStart, currEnd});

    return results.toArray(new int[results.size()][]);
  }

  public static void main(String[] args) {
    MergeIntervals mergeIntervals = new MergeIntervals();
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {12, 16}};
    int[][] result = mergeIntervals.merge(intervals);
    for (int[] interval : result) {
      System.out.println(Arrays.toString(interval));
    }
  }
}
