// https://www.hackerrank.com/challenges/bigger-is-greater/problem?isFullScreen=true
package hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class BiggerIsGreater {
  public static String biggerIsGreater(String w) {
    int len = w.length();
    int pivot = len - 1;
    for(int i = len - 2; i>=0;i--) {
      if(w.charAt(i) < w.charAt(i+1)) {
         pivot = i;
         break;
      }
    }

    if(pivot == len - 1) {
      return "no answer";
    }

    int max = pivot+1;
    for(int i = pivot+2; i < len;i++) {
      if(w.charAt(i) > w.charAt(pivot) && w.charAt(max) > w.charAt(i)) {
        max = i;
      }
    }

    char[] c = w.toCharArray();

    swapChar(c,pivot,max);

    for(int i = pivot+1;i<len -1 ; i++) {
      for(int j = i+1; j< len ; j++) {
        if(c[i] > c[j]) {
          swapChar(c,i,j);
        }
      }
    }

    return new String(c);
  }

  public static void swapChar(char[] c, int pos1, int pos2) {
    char temp = c[pos1];
    c[pos1] = c[pos2];
    c[pos2] = temp;
  }
  public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("hackerrank/text.txt"));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        for (int TItr = 0; TItr < T; TItr++) {
            String w = bufferedReader.readLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
