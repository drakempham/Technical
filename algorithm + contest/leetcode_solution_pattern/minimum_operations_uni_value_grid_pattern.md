# Beginner Friendly ⛳️ || Math & Sorting 🧮 || Median Target 🎯

## Intuition

If we want to make all elements in a grid equal by adding or subtracting `x`, it's exactly like moving all numbers to a single target value. The mathematical target that minimizes the total distance (or operations) for a list of numbers is always its **median**. However, before doing that, we must ensure it's even possible: all numbers must be aligned on the same "step size", meaning they must have the exact same remainder when divided by `x`.

## Why This Works

Think of the elements as houses on a number line, and `x` is the exact distance you can travel in one step. We can only gather everyone at the same house if they sit on the same valid grid points (i.e., same remainder modulo `x`). If they do, the optimal meeting point to minimize the total travel distance is the median house. Choosing any other point would increase the distance from one half of the houses more than it decreases the distance from the other half!

## Approach

- **Step 1:** Grab the remainder of the very first element (`grid[0][0] % x`). This serves as our reference remainder.
- **Step 2:** Flatten the 2D grid into a 1D list. As we flatten, instantly check if every number has the same remainder as our reference. If any number differs, we can never make them equal, so we return `-1` immediately (Fail fast).
- **Step 3:** Sort the flattened 1D list. This makes it trivial to find the median element, which will be exactly at the middle index `n // 2`.
- **Step 4:** Calculate the total operations by finding the absolute difference between each element and the median, and dividing by `x`. Sum these up and return!

## Complexity

- Time complexity: `O(N \log N)` where `N` is the total number of elements in the grid (`m * n`). Flattening and checking takes `O(N)`, but sorting the array takes `O(N \log N)`.
- Space complexity: `O(N)` because we create a new 1D array to store all elements for sorting.

## Code

**Python:**
```python
from typing import List

class Solution:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        flat_list = []
        sp_remainder = grid[0][0] % x
        
        for row in grid:
            for num in row:
                if num % x != sp_remainder:
                    return -1
                flat_list.append(num)
                
        flat_list.sort()
        n = len(flat_list)
        median_num = flat_list[n // 2]
        
        return sum(abs(num - median_num) // x for num in flat_list)
```

**Java:**
```java
import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] flatList = new int[m * n];
        int remainder = grid[0][0] % x;
        int idx = 0;
        
        for (int[] row : grid) {
            for (int num : row) {
                if (num % x != remainder) {
                    return -1;
                }
                flatList[idx++] = num;
            }
        }
        
        Arrays.sort(flatList);
        int medianNum = flatList[(m * n) / 2];
        
        int totalOps = 0;
        for (int num : flatList) {
            totalOps += Math.abs(num - medianNum) / x;
        }
        
        return totalOps;
    }
}
```

## Similar problem

Some example problems with same pattern
- 462. Minimum Moves to Equal Array Elements II 🧠
- 296. Best Meeting Point 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
