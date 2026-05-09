# Python 🐍 Java ☕ || Counting 🔢 || Furthest Distance From Origin 🚀

## Intuition

The robot starts at position `0` on a number line. Each move is `'L'` (go left), `'R'` (go right), or `'_'` (wildcard — can go either way). We want to **maximize** the final distance from `0`.

The key insight is: the wildcards `'_'` should always be assigned to the **dominant direction** (whichever of L or R appears more). This maximizes the imbalance between left and right, which in turn maximizes the final distance.

## Why This Works

Think of it this way:

- The fixed moves `'L'` and `'R'` create a **net displacement** of `|left_count - right_count|`.
- Every wildcard `'_'` adds `+1` to that displacement if we push it in the dominant direction.
- So the answer is simply: **wildcards + net displacement of fixed moves**.

We never need to simulate any actual movement — just count.

## Approach

- **Step 1:** Initialize `left_counter = 0` and `right_counter = 0`.
- **Step 2:** Loop through each character in `moves`:
  - If `'L'`, increment `left_counter`.
  - If `'R'`, increment `right_counter`.
  - If `'_'`, do nothing (we handle it separately).
- **Step 3:** Compute the wildcard count:
  ```
  underline_counter = len(moves) - left_counter - right_counter
  ```
- **Step 4:** Return the answer:
  ```
  underline_counter + abs(left_counter - right_counter)
  ```
  - `abs(left_counter - right_counter)` is the net displacement from fixed moves.
  - `underline_counter` adds each wildcard to the dominant direction.

## Complexity

- Time complexity: `O(N)`
  We make a single pass through the `moves` string of length `N`.
- Space complexity: `O(1)`
  Only three integer counters are used, regardless of input size.

## Code

**Python:**
```python
class FurthestDistanceFromOrigin:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        right_counter = 0
        left_counter = 0
        for move in moves:
            if move == 'L':
                left_counter += 1
            elif move == 'R':
                right_counter += 1
        underline_counter = len(moves) - left_counter - right_counter
        return underline_counter + abs(left_counter - right_counter)
```

**Java:**
```java
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int leftCounter = 0;
        int rightCounter = 0;
        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                leftCounter++;
            } else if (move == 'R') {
                rightCounter++;
            }
        }
        int underlineCounter = moves.length() - leftCounter - rightCounter;
        return underlineCounter + Math.abs(leftCounter - rightCounter);
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
