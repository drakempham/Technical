package day21012026;

public class GasStation {
  // The solution is that when start from gas A, you can come to any position in
  // circuit way without worry
  // currentGast - cost[i] < 0
  // so reverse the idea, if start from A to B is negative, the result can't be in
  // between A and B
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int currentGas = 0;
    int totalGasCost = 0;
    int position = 0;
    for (int i = 0; i < gas.length; i++) {
      currentGas += gas[i] - cost[i];
      totalGasCost += gas[i] - cost[i];

      if (currentGas < 0) {
        currentGas = 0;
        position = i + 1; // position is the next one;
      }
    }

    return totalGasCost >= 0 ? position : -1;
  }

  // as first seen, we can see the solution should be the first one positive
  // gas[i] - cost[i] -> 4-1 = 3
  public static void main(String[] args) {
    GasStation solution = new GasStation();
    int[] gas = { 1, 2, 3, 4, 5 };
    int[] cost = { 3, 4, 5, 1, 2 };
    System.out.println(solution.canCompleteCircuit(gas, cost));
  }
}
