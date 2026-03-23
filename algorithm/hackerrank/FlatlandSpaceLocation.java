// problem: https://www.hackerrank.com/challenges/flatland-space-stations/problem?isFullScreen=true
package hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FlatlandSpaceLocation {

    // Complete the flatlandSpaceStations function below.
    static int flatlandSpaceStations(int n, int[] c) {
        int maxDis = 0;
        for(int i = 0; i< n; i++) {
          int nearestStationOneDist = Integer.MAX_VALUE;
          for(int station: c) {
            nearestStationOneDist = Math.min(nearestStationOneDist, Math.abs(station-i));
          }

          maxDis = Math.max(maxDis,nearestStationOneDist);
        }

        return maxDis;
    }

    private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("text.txt"));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = scanner.nextLine().split(" ");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = flatlandSpaceStations(n, c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
