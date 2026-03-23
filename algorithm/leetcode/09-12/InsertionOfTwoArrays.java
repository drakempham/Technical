import java.util.ArrayList;
import java.util.HashMap;

public class InsertionOfTwoArrays {
  public int[] intersect(int[] nums1, int[] nums2) {
    var map = new HashMap<Integer, Integer>();

    for (int ele : nums1) {
      map.put(ele, map.getOrDefault(ele, 0) + 1);
    }

    
    var result = new ArrayList<Integer>();
    for (int ele: nums1) {
      if (map.getOrDefault(ele, 0) > 0) {
        result.add(ele);
        map.put(ele, map.get(ele) -1);
      }
    }

      // return new int[] {};
      return result.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) {
    int[] arr1 = { 1, 2, 2, 1 };
    int[] arr2 = { 2, 2 };
    InsertionOfTwoArrays sol = new InsertionOfTwoArrays();
    int[] result = sol.intersect(arr1, arr2);
    for (int ele : result) {
      System.out.print(ele + " ");
    }
  }
}
