# Python 🔥 || Math & Greedy Approach 🧠 || Constant Space 🚀 || Integer Break ⛳️

## Intuition

To maximize the product of the parts, we should break the number `n` into as many `3`s as possible. If we break it into parts of `2` or `3`, we get a larger product than keeping larger numbers intact. However, since $3 \times 3 > 2 \times 2 \times 2$, we prefer using `3`s over `2`s. The only exception is when the remaining sum is `4` (which is better kept as `4` since $4 > 3 \times 1$).

## Approach

- **Step 1:** Handle the base cases where `n` is `2` or `3`. For `n = 2`, the only valid break is `1 + 1` (product `1`). For `n = 3`, the only valid break is `2 + 1` (product `2`).
- **Step 2:** For numbers greater than `3`, initialize the product `ans` to `1`.
- **Step 3:** Use a loop to repeatedly subtract `3` from `n` and multiply `ans` by `3`, as long as `n > 4`. 
- **Step 4:** When `n` becomes `4` or less, simply multiply the remaining `n` into `ans` and break the loop.
- **Step 5:** Return the accumulated product `ans`.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(1)`

## Code

```python
class Solution:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
            
        ans = 1
        while True:
            if n <= 4:
                ans *= n
                break
            else:
                ans *= 3
                n -= 3
            
        return ans
```

## Why This Works

Any integer greater than 4 can be broken down into parts of 2 and 3 to yield a larger product (e.g., $5 = 3 + 2$, $6 = 3 + 3$). Since $3 \times 3 > 2 \times 2 \times 2$, greedily taking out `3`s will maximize the product. We stop subtracting `3` when the remaining number `n` reaches `4`, because keeping it as `4` is better than breaking it into $3 + 1$ ($4 > 3 \times 1$). Thus, repeatedly pulling out `3`s leaves us with the optimal remaining factor.

## 🧠 Mathematical Deep Dive: Why 3?

If you are curious why `3` is the magic number, we can prove it using calculus!

1. **Step 1:** Imagine breaking a large integer `N` into equal parts of size `x`. The total number of parts will be `N / x`.
2. **Step 2:** The total product of these parts will be $P = x^{(N / x)}$. 
3. **Step 3:** To maximize $P$, it is mathematically easier to maximize its natural logarithm. We take the natural log of both sides: 
   $\ln(P) = \frac{N}{x} \ln(x) = N \cdot \frac{\ln(x)}{x}$
4. **Step 4:** Since `N` is a fixed constant, our goal simply becomes maximizing the function: 
   $f(x) = \frac{\ln(x)}{x}$
5. **Step 5:** To find the maximum point of this function, we take its derivative (đạo hàm) with respect to $x$ using the quotient rule:
   $$f'(x) = \frac{\frac{1}{x} \cdot x - \ln(x) \cdot 1}{x^2} = \frac{1 - \ln(x)}{x^2}$$
6. **Step 6:** We set the derivative to `0` to find the peak (critical point):
   $$1 - \ln(x) = 0 \implies \ln(x) = 1 \implies x = e \approx 2.718...$$
7. **Step 7:** Since $x$ must be a whole integer, we check the two integers closest to Euler's number $e$ (`2` and `3`) to see which yields a larger result for $x^{1/x}$:
   - For $x = 2$: $2^{1/2} = \sqrt{2} \approx 1.414$
   - For $x = 3$: $3^{1/3} = \sqrt[3]{3} \approx 1.442$

**Conclusion:** Because $1.442 > 1.414$, breaking the number into `3`s mathematically guarantees a larger product than breaking it into `2`s. This is the exact reason why our greedy approach aggressively subtracts `3`!

---

If this explanation helped, feel free to upvote it.
