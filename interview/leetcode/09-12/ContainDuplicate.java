
import java.util.HashSet;

public class ContainDuplicate {
  public boolean containsDuplicate(int[] nums) {
    var set = new HashSet<Integer>();
    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
      } else {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 1 };
    ContainDuplicate sol = new ContainDuplicate();
    System.out.println(sol.containsDuplicate(arr)); // Output: true
  }
}
