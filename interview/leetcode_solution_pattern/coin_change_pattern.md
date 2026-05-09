# Coin by Coin, Build the Minimum || Beginner Friendly ⛳️ || Dynamic Programming

## Intuition

For every amount from `0` to `amount`, we want to know the minimum number of coins needed to build it.

The key idea is simple:

If we already know the best answer for `i - coin`, then we can use one more `coin` to make amount `i`.

So the transition becomes:

`dp[i] = min(dp[i], dp[i - coin] + 1)`

This is a classic bottom-up Dynamic Programming pattern. Instead of trying all combinations recursively, we build answers for smaller amounts first, then use them to solve bigger amounts.

## Why This Works

Let `dp[i]` mean the minimum number of coins needed to make amount `i`.

For any final amount `i`, the last coin used must be one of the coins in `coins`. If that last coin has value `coin`, then before taking it, we must have already formed amount `i - coin`.

So the best answer for `i` is the minimum over all possible last coins:

`dp[i - coin] + 1`

Because we process amounts from small to large, `dp[i - coin]` has already been computed before we use it.

## Approach

- **Step 1:** Create a `dp` array of size `amount + 1`.
- **Step 2:** Initialize every value as infinity because we have not found a valid way to form those amounts yet.
- **Step 3:** Set `dp[0] = 0`, because we need `0` coins to make amount `0`.
- **Step 4:** Loop through every target amount `i` from `1` to `amount`.
- **Step 5:** For each coin, check if `coin <= i`.
- **Step 6:** If it fits, try using this coin as the last coin and update `dp[i]`.
- **Step 7:** After filling the table, return `dp[amount]` if it is reachable. Otherwise, return `-1`.

## Complexity

- Time complexity: `O(amount * n)`, where `n` is the number of coins.
- Space complexity: `O(amount)`, because we store one DP value for each amount from `0` to `amount`.

## Code

**Python:**

```python
from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [float("inf")] * (amount + 1)
        dp[0] = 0

        for current_amount in range(1, amount + 1):
            for coin in coins:
                if coin <= current_amount:
                    dp[current_amount] = min(
                        dp[current_amount],
                        dp[current_amount - coin] + 1
                    )

        return dp[amount] if dp[amount] != float("inf") else -1
```

## Similar Problem

Some example problems with same pattern
- 279. Perfect Squares 🧠
- 322. Coin Change 🧠
- 377. Combination Sum IV 🧠
- 518. Coin Change II 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
