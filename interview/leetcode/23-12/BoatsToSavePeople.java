import java.util.Arrays;

public class BoatsToSavePeople {
  // idea: we will try to combine the weightnest and the lightness person on same
  // boat to optimize weight
  // imagine you are trying to put marbles into the jar, put the small ball in the
  // space between big one
  public int numRescueBoats(int[] people, int limit) {
    if (people.length == 1) {
      return 1;
    }
    Arrays.sort(people);
    int boatCounter = 0;
    int left = 0;
    int right = people.length - 1;
    while (left < right) {
      boatCounter += 1;
      if (people[left] + people[right] <= limit) {
        left++;
        right--;
      } else {
        right--; // boat only contains the people weight more
      }
    }

    if (left == right) {
      boatCounter += 1;
    }

    return boatCounter;
  }

  public static void main(String[] args) {
    BoatsToSavePeople sol = new BoatsToSavePeople();
    int[] people = { 3, 2, 2, 1 };
    int limit = 3;
    System.out.println(sol.numRescueBoats(people, limit));

    int[] people2 = { 3, 5, 3, 4 };
    int limit2 = 5;
    System.out.println(sol.numRescueBoats(people2, limit2));
  }
}
