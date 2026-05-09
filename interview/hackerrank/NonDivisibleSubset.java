// problem: https://www.hackerrank.com/challenges/non-divisible-subset/problem?isFullScreen=true
package hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class NonDivisibleSubset {

  public static int nonDivisibleSubset(int k, List<Integer> s) {
    // calculate frequency of remainers
    int[] arr = new int[k];
    int maxSubArr = 0;
    for(int i = 0 ; i < s.size() ;i ++) {
      int remain = s.get(i) % k;
      if(remain < 0) {
        remain += k;
      }

      arr[remain] ++;
    }

    for(int i = 1; i <= k/2; i++) {
      int remain = k - i;
      if(i!=remain) {
        maxSubArr += Math.max(arr[i],arr[remain]);
      }
      else {
        maxSubArr += Math.min(1,arr[i]);
      }
    }

    if(arr[0] > 0) {
      maxSubArr ++;
    }

    return maxSubArr;
  }

  public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] sTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> s = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int sItem = Integer.parseInt(sTemp[i]);
            s.add(sItem);
        }

        int result = nonDivisibleSubset(k, s);
        System.out.println("result "+ result);
    }
}
// 7 4
// 19 10 12 10 24 25 22