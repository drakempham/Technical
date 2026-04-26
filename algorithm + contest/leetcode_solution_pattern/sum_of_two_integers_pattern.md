# Python 🐍 Java ☕ || Bit Manipulation 🧠 || Add Without `+` 🚀 || Sum of Two Integers

## Intuition

Normal addition can be split into two parts:

- `a ^ b` gives the sum without carry
- `(a & b) << 1` gives the carry that must be added in the next round

So instead of using `+`, we repeatedly combine these two pieces until there is no carry left.

For Python, we also need a small trick for negative numbers. Python integers do not have a fixed 32-bit size, so negative numbers behave like they have infinitely many leading `1` bits. To simulate normal 32-bit signed integer behavior, we use a `mask`.

## Why This Works

For each bit position:

- XOR represents addition without carry:
  - `0 + 0 = 0`
  - `1 + 0 = 1`
  - `0 + 1 = 1`
  - `1 + 1 = 0` with a carry
- AND finds exactly where both bits are `1`, which means a carry is created.
- Shifting the carry left by one moves it to the next higher bit.

Repeating this process is the same as propagating carries in regular addition. When the carry becomes `0`, `a` contains the final sum.

## Approach

- **Step 1:** Use `a ^ b` to calculate the partial sum without carry.
- **Step 2:** Use `(a & b) << 1` to calculate the carry.
- **Step 3:** Repeat until the carry becomes `0`.
- **Step 4:** In Python, apply a 32-bit mask during each step to avoid infinite sign extension for negative numbers.
- **Step 5:** Convert the final unsigned 32-bit result back to a signed integer if needed.

## Complexity

- Time complexity: `O(1)`
- Space complexity: `O(1)`

The loop is bounded by the fixed 32-bit integer size.

## Code

**Python:**
```python
class Solution:
    def getSum(self, a: int, b: int) -> int:
        mask = (1 << 32) - 1
        max_int = (1 << 31) - 1

        while b != 0:
            a, b = (a ^ b) & mask, ((a & b) << 1) & mask

        return a if a <= max_int else ~(a ^ mask)
```

**Java:**
```java
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }

        return a;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
