package day27012026;

public class MergeTriplets {
  public boolean mergeTriplets(int[][] triplets, int[] target) {
    boolean foundX = false, foundY = false, foundZ = false;
    for (int[] triplet : triplets) {
      // cause any element greater than any elements still make the sum exceed target
      if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {
        if (triplet[0] == target[0])
          foundX = true;
        if (triplet[1] == target[1])
          foundY = true;
        if (triplet[2] == target[2])
          foundZ = true;
      }

      if (foundX && foundY && foundZ) {
        return true;
      }
    }

    return foundX && foundY && foundZ;
  }

  public static void main(String[] args) {
    MergeTriplets solution = new MergeTriplets();
    System.out.println(solution.mergeTriplets(new int[][] { { 1, 2, 3 }, { 7, 1, 1 } }, new int[] { 7, 2, 3 }));
  }
}
