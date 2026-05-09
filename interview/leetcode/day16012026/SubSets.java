import java.util.ArrayList;
import java.util.List;

public class SubSets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    // backtracking based on result, current temp, start to add into temp
    backtracking(nums, result, new ArrayList<>(), 0);

    return result;
  }

  public void backtracking(int[] nums, List<List<Integer>> result, List<Integer> temp, int start) {
    // everytime always add temp, because temp cannot be loop caused we fixed window start ->
    // remmeber to reset temp so it don't affect another one
    result.add(new ArrayList<>(temp));

    for (int i = start; i < nums.length; i++) {
      temp.add(nums[i]);
      backtracking(nums, result, temp, i + 1);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    SubSets subSets = new SubSets();
    int[] nums = {1, 2, 3};
    List<List<Integer>> result = subSets.subsets(nums);
    System.out.println(result);
  }
}
