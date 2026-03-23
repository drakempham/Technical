package day22022026;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionOf2Arr {

  // speed : (Omlogm + nlogn)
  // space: O(1)
  public int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    List<Integer> store = new ArrayList<>();
    int first = 0, second = 0;
    while (first < nums1.length && second < nums2.length) {
      if (nums1[first] == nums2[second]) {
        if (store.isEmpty() || !store.contains(nums1[first])) {
          store.add(nums1[first]);

        }
        first++;
        second++;
      } else if (nums1[first] < nums2[second]) {
        first++;
      } else {
        second++;
      }
    }

    return store.stream().mapToInt(Integer::intValue).toArray();
  }

  // memory :O(m) (or O(n))
  // speed; O(m+n)
  public int[] intersect2(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<>();
    for (int i : nums1)
      set1.add(i);
    Set<Integer> intersect = new HashSet<>();
    for (int i : nums2) {
      if (set1.contains(i)) {
        intersect.add(i);
      }
    }

    return intersect.stream().mapToInt(Integer::intValue).toArray();
  }

  public static void main(String[] args) {
    IntersectionOf2Arr sol = new IntersectionOf2Arr();
    int[] result = sol.intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 });
    Arrays.stream(result).forEach(ele -> System.out.println(ele));
    result = sol.intersection(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 });
    Arrays.stream(result).forEach(ele -> System.out.println(ele));
  }

}
