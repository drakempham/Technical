public class MedianOfTwoSortedArrays {
  // merge sort and find median
  // time: O(m+n), space: O(m+n)
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int[] merged = new int[m + n];
    int i = 0, j = 0, k = 0;
    while (i < m && j < n) {
      if (nums1[i] < nums2[j]) {
        merged[k++] = nums1[i++];
      } else {
        merged[k++] = nums2[j++];
      }
    }

    while (i < m) {
      merged[k++] = nums1[i++];
    }

    while (j < n) {
      merged[k++] = nums2[j++];
    }

    if ((m + n) % 2 == 0) {
      return merged[(m + n) / 2 - 1] / 2.0 + merged[(m + n) / 2] / 2.0;
    }

    return merged[(m + n) / 2];
  }

  // two-pointer approach without extra space
  // time: O(m+n), space: O(1)
  public double findMedianSortedArraysTwoPointer(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int i = 0, j = 0;
    int curr = 0, prev = 0;

    for (int count = 0; count <= (m + n) / 2; count++) {
      // previous = current and move up the current element
      prev = curr;

      if (i < m && (j >= n || nums1[i] < nums2[j])) {
        curr = nums1[i];
        i++;
      } else {
        curr = nums2[j];
        j++;    
      }
    }

    if ((m + n) % 2 == 0) {
      return (prev + curr) / 2.0;
    }

    return curr;
  }

  // binary search approach
  // time: O(log(min(m,n))), space: O(1)
  // split the arrays into two partition, partitionX and partitionY such that each partition split each array into two parts, left and right
  // partitionX = (m+n+1)/2 -
  // leftX <= rightY and leftY <= rightX
  // if the total number of elements is even, the median is the average of the two middle elements
  // if the total number of elements is odd, the median is the middle element

  public double findMediaOfTwoSortedArraysBinarySearch(int[] a, int[] b) {
    // binary search on smaller array
    if (a.length > b.length) {
      return findMediaOfTwoSortedArraysBinarySearch(b, a);
    }

    int m = a.length, n = b.length;
    int left =0, right = m;

    while (left <= right) {
      int partitionX = left + (right - left) / 2; // left partition more than right one - choose left
      int partitionY = (m + n + 1) / 2 - partitionX;

      int maxleftX = partitionX == 0 ? Integer.MIN_VALUE : a[partitionX - 1];
      int maxleftY = partitionY == 0 ? Integer.MIN_VALUE : b[partitionY - 1];

      int minRightX = partitionX == m ? Integer.MAX_VALUE : a[partitionX];
      int minRightY = partitionY == n ? Integer.MAX_VALUE : b[partitionY];

      if (maxleftX <= minRightY && maxleftY <= minRightX) {
        if ((m + n) % 2 == 1) {
          return Math.max(maxleftX, maxleftY);
        } else {
          return (Math.max(maxleftX, maxleftY) + Math.min(minRightX, minRightY)) / 2.0;
        }
      } else if (maxleftX > minRightY) {
        right = partitionX - 1;
      } else {
        left = partitionX + 1;
      }
    }

    return -1; // this can't happen
  }
  public static void main(String[] args) {
    MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
    int[] nums1 = { 1, 3 };
    int[] nums2 = { 2 };
    double result = solution.findMediaOfTwoSortedArraysBinarySearch(nums1, nums2);
    System.out.println("Median of two sorted arrays: " + result); // Output: 2.0

    int[] nums3 = { 1, 2 };
    int[] nums4 = { 3, 4 };
    double result2 = solution.findMediaOfTwoSortedArraysBinarySearch(nums3, nums4);
    System.out.println("Median of two sorted arrays (two-pointer): " + result2); // Output: 2.5
  }
}
