package day27012026;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
  public List<Integer> partitionLabels(String s) {
    if (s.length() == 0) {
      return new ArrayList<>();
    }

    int[] lastPos = new int[26];
    for (int i = 0; i < s.length(); i++) {
      lastPos[s.charAt(i) - 'a'] = i;
    }

    List<Integer> result = new ArrayList<>();
    int start = 0;
    int end = -1;
    for (int i = 0; i < s.length(); i++) {
      end = Math.max(lastPos[s.charAt(i) - 'a'], end);

      if (i == end) {
        result.add(end - start + 1);
        start = i + 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    PartitionLabels solution = new PartitionLabels();
    System.out.println(solution.partitionLabels("xyxxyzbzbbisl"));
  }

}
