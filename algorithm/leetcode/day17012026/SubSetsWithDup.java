import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetsWithDup {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(result, nums, new ArrayList<>(), 0);
    return result;
  }

  public void backtrack(List<List<Integer>> result, int[] nums, List<Integer> temp, int start) {
    result.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      temp.add(nums[i]);
      backtrack(result, nums, temp, i + 1);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    SubSetsWithDup subSetsWithDup = new SubSetsWithDup();
    int[] nums = {1, 2, 1};
    List<List<Integer>> result = subSetsWithDup.subsetsWithDup(nums);
    System.out.println(result);
  }
}
