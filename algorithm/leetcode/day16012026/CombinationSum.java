import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    backtracking(nums, target, result, new ArrayList<>(), 0);

    return result;
  }

  public void backtracking(int[] nums, int remain, List<List<Integer>> result, List<Integer> temp,
      int start) {
    if (remain == 0) {
      result.add(new ArrayList<>(temp));
    }

    for (int i = start; i < nums.length; i++) {
      // if we add nums[i] in this case, the sum sure always large than remain
      if (remain < nums[i])
        break;

      temp.add(nums[i]);
      backtracking(nums, remain - nums[i], result, temp, i); // the list can be dup so keep i
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    CombinationSum obj = new CombinationSum();
    System.out.println(obj.combinationSum(new int[] {2, 5, 6, 9}, 9));
  }
}
