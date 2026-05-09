package day092026;

import java.util.PriorityQueue;

public class KthLargestElementInArr {
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
    for (int num : nums) {
      pq.add(num);
      if (pq.size() > k) {
        pq.poll();
      }
    }

    return pq.peek();

  }

}
