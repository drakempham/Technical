package hackerrank;
// problem: https://www.hackerrank.com/challenges/queens-attack-2/problem?isFullScreen=true

import java.io.*;
import java.util.*;

public class QueenAttackII {

  public static int queensAttack(int n, int k, int rq, int cq, List<List<Integer>> obstacles) {
       // Initialize count
       int count = 0;
        
       // Set to store obstacle positions for faster lookup
       Set<List<Integer>> obstaclesSet = new HashSet<>(obstacles);
       
       // Define directions (up, down, left, right, diagonals)
       int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
       
       // Check each direction
       for (int[] dir : directions) {
           int dr = dir[0];
           int dc = dir[1];
           int r = rq + dr;
           int c = cq + dc;
           while (r >= 1 && r <= n && c >= 1 && c <= n && !obstaclesSet.contains(Arrays.asList(r, c))) {
               count++;
               r += dr;
               c += dc;
           }
       }
       
       return count;
  }

  public static boolean checkIsEqual(List<Integer> pos, List<Integer> obstacle) {
    if(pos.get(0) == obstacle.get(0) && pos.get(1) == obstacle.get(1)) {
      return true;
    }

    return false;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("text.txt"));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int k =  Integer.parseInt(firstMultipleInput[1]);

    String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int r_q = Integer.parseInt(secondMultipleInput[0]);

    int c_q = Integer.parseInt(secondMultipleInput[1]);

    List<List<Integer>> obstacles = new ArrayList<>();

    for (int i = 0; i < k; i++) {
        String[] obstaclesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> obstaclesRowItems = new ArrayList<>();

        for (int j = 0; j < 2; j++) {
            int obstaclesItem = Integer.parseInt(obstaclesRowTempItems[j]);
            obstaclesRowItems.add(obstaclesItem);
        }

        obstacles.add(obstaclesRowItems);
    }

    int result = queensAttack(n, k, r_q, c_q, obstacles);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
