
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, nums, new ArrayList<>(), new HashSet<>());
    return result;
  }

  // this set here store idx
  public void backtrack(List<List<Integer>> result, int[] nums, List<Integer> temp,
      Set<Integer> visited) {
    if (temp.size() == nums.length) {
      result.add(new ArrayList<>(temp));
    }

    // it can start from anywhere so start from 0 to not miss any number
    for (int i = 0; i < nums.length; i++) {
      if (visited.contains(i)) {
        continue;
      }

      temp.add(nums[i]);
      visited.add(i);
      backtrack(result, nums, temp, visited);
      visited.remove(i);
      temp.remove(temp.size() - 1);

    }
  }

  public static void main(String[] args) {
    Permutation permutation = new Permutation();
    int[] nums = {1, 2, 3};
    List<List<Integer>> result = permutation.permute(nums);
    System.out.println(result);
  }

}
