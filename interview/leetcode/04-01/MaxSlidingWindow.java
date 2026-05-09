
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxSlidingWindow {
  // Implement using Sliding window + priority queue
  // time complexity: O(n2)
  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1]; // k windows
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
      if (a[0] > b[0]) {// a priority higher than b -> b-a < 0 so a prioerity more than b
        return b[0] - a[0];
      }

      return b[0] - a[0];
    });

    for (int i = 0; i < nums.length; i++) {
      pq.offer(new int[] { nums[i], i });

      // remove when the largest element outside of array
      if (i >= k - 1) { // start to add result
        while (pq.peek()[1] < i - k + 1) {
          pq.poll();
        }

        res[i - k + 1] = pq.peek()[0];
      }
    }

    return res;
  }

  public int[] maxSlidingWindowDeque(int[] nums, int k) {
    int n = nums.length;
    int[] res = new int[n - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
        deque.pollFirst();
      }

      while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
        deque.pollLast();
      }

      deque.offerLast(i);

      if (i >= k - 1) {
        res[i - k + 1] = nums[deque.peekFirst()];
      }
    }

    return res;
  }

  public static void main(String[] args) {
    MaxSlidingWindow solution = new MaxSlidingWindow();
    int[] nums = { 1, 3, -1, -3, 5, 3, 8, 7 };
    int k = 3;
    int[] result = solution.maxSlidingWindowDeque(nums, k);
    for (int num : result) {
      System.out.print(num + " ");
    }
    // Output: [3,3,5,5,6,7]

  }
}
