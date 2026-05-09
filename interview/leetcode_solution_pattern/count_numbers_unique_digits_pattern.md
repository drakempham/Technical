# Python 🐍 Java ☕ || Math & Combinatorics || One-Pass Iterative || Count Numbers with Unique Digits

## Intuition

At each digit length, we want to count how many numbers exist where **no digit repeats**. The key insight is that we can count these using basic combinatorics:

- The **first digit** (leftmost) can be anything from `1–9` → **9 choices** (no leading zeros).
- The **second digit** can be `0–9` minus the first digit → **9 choices**.
- The **third digit** → **8 choices**.
- And so on, decreasing by 1 each time.

The total count for all numbers up to `n` digits is the **sum of unique counts for each digit length** from `1` to `n`, plus the number `0` itself.

## Why This Works

For a number with exactly `k` digits:
- The first digit has 9 choices (1 through 9).
- Each subsequent digit has one fewer choice than the last (we exclude all already-used digits).
- So the count of `k`-digit unique numbers = `9 × 9 × 8 × 7 × ... × (10 - k + 1)`.

By iterating from length `2` to `n` and accumulating these products, we correctly count every valid number without brute-forcing or backtracking.

## Approach

- **Step 1:** Handle base case `n == 0`: return `1` (only the number `0`).
- **Step 2:** Start `res = 10`, which accounts for all 1-digit numbers (`0` through `9`).
- **Step 3:** Initialize `total_opt = 9` (choices for the first digit) and `available_opt = 9` (choices for the next digit).
- **Step 4:** Loop from `2` to `n` (inclusive). In each iteration, multiply `total_opt` by `available_opt` to get the count of unique numbers at the current digit length, then add it to `res`.
- **Step 5:** Decrease `available_opt` by 1 before the next iteration.
- **Step 6:** Return `res`.

## Complexity

- Time complexity: `O(N)` — one loop from `2` to `n`.
- Space complexity: `O(1)` — only a few variables, no recursion or extra data structures.

## Code

**Python:**
```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        if n == 0:
            return 1

        total_opt = 9      # choices for the first digit (1–9)
        available_opt = 9  # choices for each subsequent digit
        res = 10           # base: all 1-digit numbers (0–9)

        for _ in range(2, n + 1):
            total_opt *= available_opt
            res += total_opt
            available_opt -= 1

        return res
```

**Java:**
```java
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;

        int totalOpt = 9;      // choices for the first digit (1–9)
        int availableOpt = 9;  // choices for each subsequent digit
        int res = 10;          // base: all 1-digit numbers (0–9)

        for (int i = 2; i <= n; i++) {
            totalOpt *= availableOpt;
            res += totalOpt;
            availableOpt--;
        }

        return res;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
