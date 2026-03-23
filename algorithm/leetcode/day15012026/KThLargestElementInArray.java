import java.util.PriorityQueue;
import java.util.Random;

public class KThLargestElementInArray {
  // using priorityQueue
  // complexity is (ONlogk)
  public int findKthLargest1(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) {
      pq.add(num);

      if (pq.size() > k) {
        pq.poll();
      }
    }

    return pq.peek();
  }

  // using pivot quick select - not sorting completely
  // complexity is O(N), space is O(1)
  public int findKthLargest(int[] nums, int k) {
    int targetIdx = nums.length - k;
    return quickSelect(nums, 0, nums.length - 1, targetIdx);
  }

  public int quickSelect(int[] nums, int left, int right, int targetIdx) {
    if (left == right) {
      return nums[left];
    }

    // destroy all rule -> prevent bad case O(n2) - cuc tri o dau, o giua, hoac o
    // cuoi
    int pivotIdx = left + new Random().nextInt(right - left + 1);
    pivotIdx = partition(nums, left, right, pivotIdx);

    if (pivotIdx == targetIdx) {
      return nums[targetIdx];
    }

    if (pivotIdx < targetIdx) {
      return quickSelect(nums, pivotIdx + 1, right, targetIdx);
    }

    return quickSelect(nums, left, pivotIdx - 1, targetIdx);
  }

  public int partition(int[] nums, int left, int right, int pivotIdx) {
    int pivotVal = nums[pivotIdx];
    swap(nums, pivotIdx, right);
    int startIdx = left;
    for (int i = left; i < right; i++) {
      if (nums[i] < pivotVal) {
        swap(nums, startIdx, i);
        startIdx++;
      }
    }

    // swap final to back pivotIdx
    swap(nums, startIdx, right);
    return startIdx;
  }

  public void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }

  public static void main(String[] args) {
    KThLargestElementInArray kthLargestElementInArray = new KThLargestElementInArray();
    int[] nums = { 3, 2, 1, 5, 6, 4 };
    int k = 2;
    System.out.println(kthLargestElementInArray.findKthLargest(nums, k));
  }
}
