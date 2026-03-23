import java.util.Arrays;

public class MinEatingSpeed {
  public int minEatingSpeed(int[] piles, int minimumHours) {
    int left = 1;
    // maximum pile length (cause its pile take minimum 1 hour to complete - not
    // async multiple piles)
    int right = Arrays.stream(piles).max().getAsInt();
    int result = Integer.MAX_VALUE;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (isEnoughTime(piles, mid, minimumHours)) {
        // check the smaller finish time ( if exist)
        right = mid - 1;
        result = Math.min(result, mid);
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  public boolean isEnoughTime(int[] piles, int speed, int minimumHours) {
    int total = 0;
    for (int i = 0; i < piles.length; i++) {
      total += ((piles[i] + speed - 1) / speed);
      if (total > minimumHours) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    MinEatingSpeed solution = new MinEatingSpeed();
    int[] piles = { 3, 6, 7, 11 };
    int h = 8;
    int result = solution.minEatingSpeed(piles, h);
    System.out.println("Minimum eating speed: " + result); // Output: 4
  }
}
