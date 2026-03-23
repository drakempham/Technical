import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElement {

  // O(n) + O(dlogd) -> n is the size of nums, d is the size of the distinct
  // elements in entries
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();

    // get frequencies
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freqMap.entrySet());
    entries.sort((a, b) -> b.getValue() - a.getValue());

    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = entries.get(i).getKey();
    }

    return result;
  }

  // O(n) + O(dlogk) -> n is the size of nums, d is the size of the distinct, the
  // heap size maximum is k (k<< n -> is most efficient)
  public int[] topKFrequent2(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    // PriorityQueue
    PriorityQueue<Map.Entry<Integer, Integer>> entries = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

    for (Map.Entry<Integer, Integer> ele : freqMap.entrySet()) {
      entries.offer(ele);

      // remove the small frequency, keep the top k frequent elements
      if (entries.size() > k) {
        entries.poll();
      }

    }

    // insert the small element poll from heap first
    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) {
      result[i] = entries.poll().getKey();
    }

    return result;
  }

  public static void main(String[] args) {
    // TopKFrequentElement solution = new TopKFrequentElement();
    int[] nums = { 1, 1, 1, 2, 2, 3 };
    int k = 2;
    // int[] result = solution.topKFrequent(nums, k);
    // for (int num : result) {
    // System.out.print(num + " ");
    // }
    // Output: [1, 2]

    TopKFrequentElement solution2 = new TopKFrequentElement();
    int[] result2 = solution2.topKFrequent2(nums, k);
    for (int num : result2) {
      System.out.print(num + " ");
    }
  }

}
