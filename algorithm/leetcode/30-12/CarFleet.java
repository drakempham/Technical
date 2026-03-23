import java.util.Arrays;

public class CarFleet {
  // if time arrival of car j is less than or equal to car i, then car j will join
  // else if time arrival of car j is greater than car i, then car j never can be
  // joined
  public int carFleet(int target, int[] position, int[] speed) {
    if (position == null || position.length == 0) {
      return 0;
    }

    int n = position.length;
    double[][] timesArrival = new double[n][2];
    int result = 1;

    for (int i = 0; i < n; i++) {
      timesArrival[i][0] = position[i];
      timesArrival[i][1] = (target - position[i]) / (speed[i] * 1.0);// times to target of each one
    }

    // sort descending , the first element s nearest target
    Arrays.sort(timesArrival, (a, b) -> Double.compare(b[0], a[0]));

    // maximum time to join fleet
    double maxTime = timesArrival[0][1];
    for (int i = 0; i < n; i++) {
      if (timesArrival[i][1] > maxTime) { // equal meet at target
        maxTime = timesArrival[i][1];
        result++;
      }
      // else if never can join fleet
    }

    return result;
  }

  public static void main(String[] args) {
    CarFleet carFleet = new CarFleet(); // int target = 10;
    // int[] position = { 1, 4 };
    // int[] speed = { 3, 2 };
    // System.out.println(carFleet.carFleet(target, position, speed)); // 1

    // int target2 = 12;
    // int[] position2 = { 10, 8, 0, 5, 3 };
    // int[] speed2 = { 2, 4, 1, 1, 3 };
    // System.out.println(carFleet.carFleet(target2, position2, speed2)); // 3

    int target3 = 10;
    int[] position3 = { 6, 8 };
    int[] speed3 = { 3, 2 };
    System.out.println(carFleet.carFleet(target3, position3, speed3)); // 2
  }
}
