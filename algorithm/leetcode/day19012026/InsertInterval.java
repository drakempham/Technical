
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    int i = 0;
    int n = intervals.length;
    // each couple has two elements
    List<int[]> result = new ArrayList<>();

    // find the first interval
    while (i < n && newInterval[0] > intervals[i][1]) {
      result.add(intervals[i]);
      i++;
    }

    // from now newInterval[0] <= interval[i][1]
    // merge interval
    while (i < n && newInterval[1] >= intervals[i][0]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }

    result.add(newInterval);

    // add the remaining
    while (i < n) {
      result.add(intervals[i]);
      i++;
    }

    return result.toArray(new int[result.size()][]);
  }

  public static void main(String[] args) {
    InsertInterval insertInterval = new InsertInterval();
    // int[][] intervals = {{1, 3}, {6, 9}};
    // int[] newInterval = {2, 5};
    // int[][] result = insertInterval.insert(intervals, newInterval);
    // for (int[] interval : result) {
    // System.out.println(Arrays.toString(interval));
    // }
    int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
    int[] newInterval = { 4, 8 };
    int[][] result = insertInterval.insert(intervals, newInterval);
    for (int[] interval : result) {
      System.out.println(Arrays.toString(interval));
    }
  }
}
