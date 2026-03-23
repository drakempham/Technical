package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ExtraLongFactorials {
   /*
     * Complete the 'extraLongFactorials' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

     public static BigInteger calculateFactorial(int n) {
       if(n == 0 || n ==1) {
        return BigInteger.ONE;
       }

       return BigInteger.valueOf(n).multiply(calculateFactorial(n-1));
     }

    public static void extraLongFactorials(int n) {
      BigInteger num = BigInteger.ONE;
      for(int i = 2; i< n ; i++) {
        num = num.multiply(BigInteger.valueOf(i));
      }

      System.out.println(num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        extraLongFactorials(n);

        bufferedReader.close();
    }
}
