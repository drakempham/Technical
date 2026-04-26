# Python 🐍 Java ☕ || Binary Search + Two Pointers 🎯 || Pigeonhole Principle 🧠 || O(N log S) 🚀

## Intuition

The problem asks us to find the maximum possible "minimum Manhattan distance" among `k` points strictly selected on a square's perimeter. Whenever we see "maximize the minimum," **Binary Search on the answer** is our best friend. 

The core challenge is efficiently checking if we can pick `k` points that are at least `threshold` distance apart. Since the points form a circle along the square's boundary, we can "unroll" the perimeter by flattening the 2D coordinates into a 1D line and duplicating the array to handle wrap-around cases seamlessly.

## Why This Works

Since the points are sorted along the perimeter, the sequence of valid jumps is monotonic. This allows us to use a **Two Pointers** approach to precalculate the next valid jump for every point in `O(N)` time.

But where do we start our jumps to form a cycle of `k` points? Testing all `N` starting points would normally take `O(N²)` time, leading to a Time Limit Exceeded (TLE). Here is where the **Pigeonhole Principle** shines: If a valid cycle of `k` jumps exists within `N` points, the shortest jump in that cycle can cover at most `N/k` points! By finding the globally shortest valid jump, we only need to test starting points within that small gap. This mathematically bounds the total number of simulated jumps to strictly `O(N)`.

## Approach

- **Step 1: Flatten and Sort.** Map each point's `(x, y)` coordinate to a 1D distance along the square's perimeter (clockwise). Sort the points based on this 1D distance.
- **Step 2: Binary Search.** Set the search space for the Manhattan distance from `left = 1` to `right = 2 * side` (the maximum possible Manhattan distance).
- **Step 3: Virtual Doubling.** For a given `threshold` distance, double the sorted points array (`2N`) to easily simulate circular wrapping without complex modulo logic.
- **Step 4: Precompute Jumps (Two Pointers).** Use two pointers `i` and `j` to build a `next_pt` array where `next_pt[i]` stores the index of the first point `j > i` that is at least `threshold` Manhattan distance away. This takes exactly `O(N)` time.
- **Step 5: O(N) Cycle Check.** Find the shortest jump `min_diff` from the `next_pt` array. If `min_diff > n // k`, it's impossible to fit `k` jumps. Otherwise, simulate `k` jumps but **only** for starting points inside the `min_diff` interval. If any simulation successfully completes `k` jumps without exceeding `start + n` (one full circle), the `threshold` is valid!

## Complexity

- Time complexity: `O(N log N + N log S)`
  where $N$ is the number of points and $S$ is the `side` length. Sorting takes `O(N log N)`. The binary search runs `log(2S)` times, and each check takes strictly `O(N)` time thanks to the Pigeonhole optimization.
- Space complexity: `O(N)` 
  to store the flattened points and the duplicated `next_pt` array.

## Code

**Python:**
```python
class Solution:
    def maxDistance(self, side: int, points: List[List[int]], k: int) -> int:
        def flatten(point: List[int]):
            x, y = point[0], point[1]
            if y == 0: return x
            if x == side: return side + y
            if y == side: return 2 * side + (side - x)
            return 3 * side + (side - y)
        
        temp = []
        for p in points:
            temp.append((flatten(p), p[0], p[1]))
        flattern_arr = sorted(temp)
        
        left = 1
        right = 2 * side
        
        def isSatisfyManhattan(threshold: int):
            n = len(flattern_arr)
            sorted_pts = [(p[1], p[2]) for p in flattern_arr]
            sorted_pts = sorted_pts + sorted_pts
            
            next_pt = [2 * n] * (2 * n)
            j = 1
            for i in range(2 * n):
                if j <= i:
                    j = i + 1
                while j < 2 * n:
                    x = sorted_pts[i][0] - sorted_pts[j][0]
                    y = sorted_pts[i][1] - sorted_pts[j][1]
                    if abs(x) + abs(y) >= threshold:
                        break
                    j += 1
                next_pt[i] = j
                
            min_diff = 2 * n + 1
            i_min = -1
            for i in range(n):
                if next_pt[i] - i < min_diff:
                    min_diff = next_pt[i] - i
                    i_min = i
                    
            if min_diff > n // k:
                return False
                
            for start_idx in range(i_min, next_pt[i_min] + 1):
                start = start_idx % n
                curr = start
                for _ in range(k):
                    curr = next_pt[curr]
                    if curr >= 2 * n:
                        break
                if curr <= start + n:
                    return True
            return False

        while left <= right:
            mid = left + (right - left) // 2
            if isSatisfyManhattan(mid):
                left = mid + 1
            else:
                right = mid - 1
        return right
```

**Java:**
```java
class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        int[][] flattened = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int flatPos = 0;
            if (y == 0) {
                flatPos = x;
            } else if (x == side) {
                flatPos = side + y;
            } else if (y == side) {
                flatPos = 2 * side + (side - x);
            } else {
                flatPos = 3 * side + (side - y);
            }
            flattened[i][0] = flatPos;
            flattened[i][1] = x;
            flattened[i][2] = y;
        }
        
        Arrays.sort(flattened, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[][] sortedPts = new int[2 * n][2];
        for (int i = 0; i < n; i++) {
            sortedPts[i][0] = flattened[i][1];
            sortedPts[i][1] = flattened[i][2];
            sortedPts[i + n][0] = flattened[i][1];
            sortedPts[i + n][1] = flattened[i][2];
        }
        
        int left = 1;
        int right = 2 * side;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isSatisfyManhattan(mid, sortedPts, n, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    private boolean isSatisfyManhattan(int threshold, int[][] sortedPts, int n, int k) {
        int[] nextPt = new int[2 * n];
        int j = 1;
        for (int i = 0; i < 2 * n; i++) {
            if (j <= i) {
                j = i + 1;
            }
            while (j < 2 * n) {
                int dist = Math.abs(sortedPts[i][0] - sortedPts[j][0]) + Math.abs(sortedPts[i][1] - sortedPts[j][1]);
                if (dist >= threshold) {
                    break;
                }
                j++;
            }
            nextPt[i] = j;
        }
        
        int minDiff = 2 * n + 1;
        int iMin = -1;
        for (int i = 0; i < n; i++) {
            if (nextPt[i] - i < minDiff) {
                minDiff = nextPt[i] - i;
                iMin = i;
            }
        }
        
        if (minDiff > n / k) {
            return false;
        }
        
        for (int startIdx = iMin; startIdx <= nextPt[iMin]; startIdx++) {
            int start = startIdx % n;
            int curr = start;
            boolean valid = true;
            for (int step = 0; step < k; step++) {
                curr = nextPt[curr];
                if (curr >= 2 * n) {
                    valid = false;
                    break;
                }
            }
            if (valid && curr <= start + n) {
                return true;
            }
        }
        
        return false;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
