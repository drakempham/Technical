package day24012026;

import java.util.TreeMap;

public class HandOfStraights {
  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length == 0) {
      return false;
    }

    if (hand.length % groupSize != 0) {
      return false;
    }

    // sort by key
    TreeMap<Integer, Integer> handCounter = new TreeMap<Integer, Integer>();
    for (int ele : hand) {
      handCounter.put(ele, handCounter.getOrDefault(ele, 0) + 1);
    }

    for (int key : handCounter.keySet()) {
      int val = handCounter.get(key);
      if (val > 0) { // skip 0
        for (int i = 1; i < groupSize; i++) {
          int nextVal = handCounter.getOrDefault(key + i, 0);
          if (nextVal < val) {
            return false;
          }

          handCounter.put(key + i, nextVal - val);
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    HandOfStraights handOfStraights = new HandOfStraights();
    int[] hand = { 1, 2, 3, 6, 2, 3, 4, 7, 8 };
    int groupSize = 3;
    boolean result = handOfStraights.isNStraightHand(hand, groupSize);
    System.out.println(result);
  }
}
