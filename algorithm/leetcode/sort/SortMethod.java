public class SortMethod {
  // merge method
  public int[] sortArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }

    int[] temp = new int[nums.length];
    mergeSort(nums, temp, 0, nums.length - 1);
    return nums;
  }

  private void mergeSort(int[] nums, int[] temp, int left, int right) {
    if (left >= right) {
      return;
    }

    int mid = left + (right - left) / 2;
    mergeSort(nums, temp, left, mid); // sort left -> mid
    mergeSort(nums, temp, mid + 1, right); // sort mid+1 -> right
    merge(nums, temp, left, mid, right);
  }

  private void merge(int[] nums, int[] temp, int left, int mid, int right) {
    for (int i = left; i <= right; i++) {
      temp[i] = nums[i];
    }

    int i = left; // left part pointer
    int j = mid + 1; // right part pointer
    int k = left;

    while (i <= mid && j <= right) {
      if (temp[i] <= temp[j]) {
        nums[k++] = temp[i++];
      } else {
        nums[k++] = temp[j++];
      }
    }

    while (i <= mid) {
      nums[k++] = temp[i++];
    }

    // actually no need to copy the rest of right part, because they are already in
    // place
    // while (j <= right) {
    // nums[k++] = temp[j++];
    // }
  }

  // heap sort. Complexity: O(nlogn), space: O(1)
  public int[] sortArray2(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }

    // build max heap ( first element is latest)
    // get the last parent to start build up. (n-1-1)/2 = n/2-1
    int n = nums.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      siftDown(nums, n, i);
    }

    // move the firstElement up and continue from last elements (n-2)
    // cay the chuan roi, chi co i la bi roi loan ( chinh lai vi tri 0)
    for (int i = n - 1; i >= 0; i--) {
      swap(nums, 0, i); // nums[0] is largest
      siftDown(nums, i, 0);
    }

    return nums;
  }

  private void siftDown(int[] nums, int n, int i) {
    while (true) {
      int largestPos = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;

      // find the maximum position
      if (left < n && nums[left] > nums[largestPos]) {
        largestPos = left;
      }

      if (right < n && nums[right] > nums[largestPos]) {
        largestPos = right;
      }

      if (largestPos == i) {
        break;
      }

      swap(nums, largestPos, i);
      i = largestPos;
    }
  }

  private void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }

  public static void main(String[] args) {
    SortMethod sorter = new SortMethod();
    // int[] nums = { 5, 2, 3, 1 };
    // int[] sorted = sorter.sortArray(nums);
    // for (int num : sorted) {
    // System.out.print(num + " ");
    // }

    int[] nums2 = { 10, 9, 1, 1, 1, 2, 3, 1 };
    int[] sorted2 = sorter.sortArray2(nums2);
    for (int num : sorted2) {
      System.out.print(num + " ");
    }
  }
}
