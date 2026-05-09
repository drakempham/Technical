import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtracking(candidates, result, target, new ArrayList<>(), 0);

    return result;
  }

  public void backtracking(int[] candidates, List<List<Integer>> result, int target,
      List<Integer> temp, int start) {
    if (target == 0) {
      result.add(new ArrayList<>(temp));
      return;
    }

    for (int i = start; i < candidates.length; i++) {
      // avoid in same loop, duplicate valuje process caused it already be processed in another
      // backtracking
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }

      if (target < candidates[i]) {
        break;
      }

      temp.add(candidates[i]);
      backtracking(candidates, result, target - candidates[i], temp, i + 1);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    CombinationSumII combinationSumII = new CombinationSumII();
    int[] candidates = {9, 2, 2, 4, 6, 1, 5};
    int target = 8;
    List<List<Integer>> result = combinationSumII.combinationSum2(candidates, target);
    System.out.println(result);
  }
}
