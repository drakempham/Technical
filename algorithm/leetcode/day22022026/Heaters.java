package day22022026;

import java.util.Arrays;

public class Heaters {
  // maximum of all shortest distance from each house to its nearest heaters
  // space: O(1)
  // memory: O(mlogn)
  public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(heaters);
    int maxDistance = 0;
    for (int house : houses) {
      maxDistance = Math.max(maxDistance, findClosestHeaters(house, heaters));
    }

    return maxDistance;
  }

  // public int findClosestHeaters(int house, int[] heaters) {
  // int left = 0, right = heaters.length - 1;
  // while (left <= right) {
  // int mid = left + (right - left) / 2;
  // if (heaters[mid] == house) {
  // return 0;
  // } else if (heaters[mid] < house) {
  // left = mid + 1;
  // } else {
  // right = mid - 1;
  // }
  // }

  // // end of the loop, the left is the nearest right of house
  // // the right is the nearest left of house

  // int distToLeft = (right >= 0) ? house - heaters[right] : Integer.MAX_VALUE;
  // int distToRight = (left < heaters.length) ? heaters[left] - house :
  // Integer.MAX_VALUE;
  // return Math.min(distToLeft, distToRight);
  // }

  public int findClosestHeaters(int house, int[] heaters) {
    int left = 0, right = heaters.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (heaters[mid] == house) {
        return 0;
      } else if (heaters[mid] < house) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    // end of the loop, the left is == right and the nerarest greatest of the mid
    int distToLeft = (left > 0) ? house - heaters[left - 1] : Integer.MAX_VALUE;

    return Math.min(Math.abs(heaters[left] - house), distToLeft);
  }

  public static void main(String[] args) {
    Heaters sol = new Heaters();
    // System.out.println(sol.findRadius(new int[] { 1, 2, 3 }, new int[] { 2 }));
    // System.out.println(sol.findRadius(new int[] { 1, 2, 3, 4 }, new int[] { 1, 4
    // }));
    System.out.println(sol.findRadius(new int[] { 1, 5 }, new int[] { 2 }));
  }
}
