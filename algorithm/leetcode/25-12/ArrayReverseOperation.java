import java.util.Arrays;

public class ArrayReverseOperation {
  // reverse array from [1...n]
  public static int[] reverseArray(int n, int[][] operations) {
    // we have n elements, and flip calculate between [l, r+1] - cause we flip r too
    // - r+1 not flip
    // so to store flipActions need n+1 elements
    int[] flipActions = new int[n + 2];
    for (int[] operation : operations) {
      flipActions[operation[0]]++;
      flipActions[operation[1] + 1]--;
    }

    // check index i is flipped or not
    boolean[] isFlipped = new boolean[n + 1];
    int currCount = 0;
    for (int i = 1; i <= n; i++) {
      currCount += flipActions[i];
      isFlipped[i] = (currCount % 2 == 1);
    }

    int[] result = new int[n];
    int left = 0, right = n - 1;
    for (int i = 0; i < n; i++) {
      if (isFlipped[i + 1]) {
        result[right] = i + 1;
        right--;
      } else {
        result[left] = i + 1;
        left++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int n = 10;
    int[][] operations = {
        { 1, 9 },
        { 1, 3 }
    };
    int[] result = reverseArray(n, operations);
    System.out.println("Kết quả: " + Arrays.toString(result));
  }
}