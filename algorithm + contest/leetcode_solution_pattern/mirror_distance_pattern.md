# Python 🐍 || Math 🧮 || Reverse Integer 🚀

## Intuition

The problem asks us to find the absolute difference between an integer and its "mirror" (the number formed by reversing its digits).
While it might be tempting to just convert the number to a string, reverse the string, and convert it back to an integer (`abs(n - int(str(n)[::-1]))`), this string-based approach has a major flaw: it breaks when dealing with negative numbers (e.g., `str(-25)[::-1]` becomes `'52-'`, which cannot be parsed as an integer). 
A cleaner and mathematically more robust way is to extract digits using modulo `10` and reconstruct the reversed number mathematically.

## Why This Works

By using math operations (`% 10` to get the last digit and `// 10` to remove it), we can build the reversed number (`rev = rev * 10 + digit`) completely independently of string formatting. For negative numbers, we process their absolute value first. If the original number was negative, its reversed version should mathematically be negative too. The distance between a negative number `n` and its negative reversed counterpart `-rev` is exactly `|n - (-rev)|`, which simplifies to `|n + rev|`.

## Approach

- **Step 1:** Take the absolute value of the input number `n` and store it in `a`. We do this to safely extract digits without worrying about the negative sign or Python's specific negative modulo behavior.
- **Step 2:** Initialize a variable `rev` to `0`. This will store our reversed integer.
- **Step 3:** Use a `while` loop as long as `a > 0`:
  - Extract the last digit of `a` using `a % 10`.
  - Append this digit to `rev` by shifting `rev` left by one decimal place (`rev * 10`) and adding the digit.
  - Remove the last digit from `a` using integer division `a //= 10`.
- **Step 4:** Return the absolute difference. If `n > 0`, the difference is simply `abs(n - rev)`. If `n <= 0`, its reversed version is technically `-rev`, so the absolute difference `|n - (-rev)|` becomes `abs(n + rev)`.

## Complexity

- Time complexity: `O(log_{10}(N))`
  The `while` loop runs exactly once for each digit in the number. The number of digits in `N` is proportional to `log_{10}(N)`.
- Space complexity: `O(1)`
  We only use a few integer variables (`a` and `rev`), requiring constant extra space. This is more memory-efficient than allocating string objects for a string-based reversal.

## Code

```python
class MirrorDistanceOfInteger:
    def mirrorDistance(self, n: int) -> int:
        a = abs(n)
        rev = 0
        while a > 0:
            rev = rev * 10 + a % 10
            a //= 10
        return abs(n - rev) if n > 0 else abs(n + rev)
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
