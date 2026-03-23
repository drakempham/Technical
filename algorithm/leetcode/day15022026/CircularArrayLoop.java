package day15022026;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/circular-array-loop/?envType=problem-list-v2&envId=two-pointers
public class CircularArrayLoop {
  // /Intuition/ Hint:
  // The cycle in this problem is special and you must consider these things
  // before deep dive into solution:

  // Cycle must has length >= 2 ( ex: -1,-2,-,3-,4,-5, self-loop end at last
  // element) -> Think about something like set / array curr_path
  // All the arrows in the loop "must" point in one direction. (it must be all
  // forward , or all backwrard) -> at each position, rememeber the first sign of
  // that element and check at every step before return result
  // Note: In case you want to module for negatvie number, please use
  // next_pos = ((i+nums[i])%n+n)%n
  // DFS find cycle
  public boolean circularArrayLoop(int[] nums) {
    if (nums == null || nums.length == 1) {
      return false;
    }
    boolean[] visited = new boolean[nums.length];
    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }
      int current = i;
      Set<Integer> currPath = new HashSet<>();
      int sign = nums[i] > 0 ? 1 : -1;
      while (true) {
        visited[current] = true;
        currPath.add(current);
        // module for both pos and neg
        int nextPos = ((current + nums[current]) % nums.length + nums.length) % nums.length;
        if (sign * nums[nextPos] < 0 || current == nextPos) {
          break;
        }

        if (currPath.contains(nextPos)) {
          // check length >= 2 -> this is the point, currPath has visited
          if (currPath.size() > 1) {
            // print the path
            System.out.println(currPath);
            return true;
          }
          // self-loop
          break;
        }

        if (visited[nextPos]) {
          break;
        }

        current = nextPos;
      }
    }

    return false;
  }

  public boolean circularArrayLoopUsingTwoPointers(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int slow = i;
      int fast = getNext(nums, slow);
      boolean isForward = nums[i] > 0;
      while (isSameDirection(nums[getNext(nums, fast)], isForward)
          && isSameDirection(nums[getNext(nums, getNext(nums, fast))], isForward)) {
        if (slow == fast) {
          // is same possition
          if (slow == getNext(nums, slow)) {
            break;
          }

          return true;
        }
        slow = getNext(nums, slow);
        fast = getNext(nums, getNext(nums, fast));
      }

    }

    return false;
  }

  // this method has two missions: stop the loop when visited (= 0), and check is
  // same direction
  public boolean isSameDirection(int val, boolean lastDirection) {
    return (val > 0) == lastDirection;
  }

  public int getNext(int[] nums, int idx) {
    return (((idx + nums[idx]) % nums.length) + nums.length) % nums.length;
  }

  public int[] testLongDeadEnd() {
    int[] nums = new int[5000];
    // Create a path: 0 -> 1 -> 2 -> ... -> 4999
    for (int i = 0; i < 5000; i++) {
      nums[i] = 1;
    }

    nums[4999] = 5000;

    return nums;
  }

  public int[] testManyDisjointLongChains() {
    int n = 5000;
    int[] nums = new int[n];

    // Create ~50 disjoint chains, each ~100 long
    int chainLen = 100;
    int numChains = n / chainLen;

    for (int c = 0; c < numChains; c++) {
      int start = c * chainLen;
      for (int i = 0; i < chainLen - 1; i++) {
        nums[start + i] = 1; // forward inside chain
      }
      // end of chain points to itself → self-loop
      nums[start + chainLen - 1] = 1; // or any k where (pos + k) % n == pos
    }

    return nums;
  }

  public static void main(String[] args) {
    CircularArrayLoop sol = new CircularArrayLoop();

    long start = System.nanoTime();

    boolean result = sol.circularArrayLoopUsingTwoPointers(sol.testLongDeadEnd());

    long end = System.nanoTime();

    long durationNs = end - start;
    double durationMs = durationNs / 1_000_000.0;
    double durationSec = durationNs / 1_000_000_000.0;

    System.out.printf("Result: %b   Time: %,d ns  (%,.3f ms)  (%.6f s)%n",
        result, durationNs, durationMs, durationSec);
  }

  // System.out.println(sol.circularArrayLoop(new int[] { 2, -1, 1, 2, 2 })); //
  // true
  // System.out.println(sol.circularArrayLoop(new int[] { 1, 1, 1, 1, 1 })); //
  // true
  // System.out.println(sol.circularArrayLoop(new int[] { -1, -2, -3, -4, -5, 6
  // })); // false
  // System.out.println(sol.circularArrayLoop(new int[] { 1, -1, 5, 1, 4 })); //
  // true
  // System.out.println(sol.circularArrayLoop(new int[] { -1, -2, -3, -4, -5 }));
  // // false

  // System.out.println(sol.circularArrayLoopUsingTwoPointers(new int[] { 2, -1,
  // 1, 2, 2 }));
  // System.out.println(sol.circularArrayLoopUsingTwoPointers(new int[] { 1, 1, 1,
  // 1, 1 })); // true
  // System.out.println(sol.circularArrayLoopUsingTwoPointers(new int[] { -1, -2,
  // -3, -4, -5, 6
  // })); // false
  // System.out.println(sol.circularArrayLoopUsingTwoPointers(new int[] { 1, -1,
  // 5, 1, 4 })); // true
  // System.out.println(sol.circularArrayLoopUsingTwoPointers(new int[] { -1, -2,
  // -3, -4, -5 })); // false
  // System.out.println(sol.circularArrayLoopUsingTwoPoiters(new int[] {-3, -1
  // })); // true

}
