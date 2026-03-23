import java.util.ArrayDeque;

public class DailyTemp {
  public int[] dailyTemperatures(int[] temperatures) {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int n = temperatures.length;
    int[] result = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
        stack.pop();
      }

      if (!stack.isEmpty()) {
        result[i] = stack.peek() - i;
      }

      stack.push(i);
    }

    return result;

  }

  public static void main(String[] args) {
    DailyTemp dailyTemp = new DailyTemp();
    int[] temperatures = { 30, 38, 30, 36, 35, 40, 28 }; // [1,4,1,2,1,0,0]
    int[] result = dailyTemp.dailyTemperatures(temperatures);
    for (int res : result) {
      System.out.print(res + " ");
    }
  }
}
