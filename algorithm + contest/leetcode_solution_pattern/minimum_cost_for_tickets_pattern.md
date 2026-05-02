# 3ms Ticket Planner || Beginner Friendly ⛳️ || Dynamic Programming || Beats 75.43% Runtime

## Intuition

We only care about the minimum money needed up to each day.

If today is not a travel day, we do not need to buy anything, so the cost stays the same as yesterday.

If today is a travel day, we have three choices:

- buy a 1-day pass
- buy a 7-day pass
- buy a 30-day pass

For each choice, we look back to the day before that pass would start covering us, then add the pass cost.

This turns the problem into a simple day-by-day Dynamic Programming problem.

## Why This Works

Let `dp[i]` mean the minimum cost needed to cover all travel days from day `1` to day `i`.

If `i` is not a travel day, then no new ticket is needed, so:

`dp[i] = dp[i - 1]`

If `i` is a travel day, then the last ticket we buy must be one of the three available passes. A 1-day pass covers only today, a 7-day pass covers the last 7 days, and a 30-day pass covers the last 30 days.

So we try all three options and keep the cheapest one.

Because we process days from left to right, every previous state we need has already been computed.

## Approach

- **Step 1:** Convert `days` into a set so we can quickly check whether a day is a travel day.
- **Step 2:** Let `last_day` be the final travel day.
- **Step 3:** Create a `dp` array of size `last_day + 1`, where `dp[i]` stores the minimum cost up to day `i`.
- **Step 4:** Set `dp[0] = 0`, because before any day starts, the cost is `0`.
- **Step 5:** Loop through every day from `1` to `last_day`.
- **Step 6:** If the current day is not a travel day, copy yesterday's cost.
- **Step 7:** If it is a travel day, take the minimum among buying a 1-day, 7-day, or 30-day pass.
- **Step 8:** Return `dp[last_day]`.

## Complexity

- Time complexity: `O(last_day)`, because we process each day once.
- Space complexity: `O(last_day)`, because we store one DP value for every day up to the last travel day.

## Code

**Python:**

```python
from typing import List


class Solution:
    def mincostTickets(self, days: List[int], costs: List[int]) -> int:
        travel_days = set(days)
        last_day = days[-1]

        dp = [float("inf")] * (last_day + 1)
        dp[0] = 0

        for day in range(1, last_day + 1):
            if day not in travel_days:
                dp[day] = dp[day - 1]
            else:
                dp[day] = min(
                    dp[day - 1] + costs[0],
                    dp[max(0, day - 7)] + costs[1],
                    dp[max(0, day - 30)] + costs[2]
                )

        return dp[last_day]
```

## Similar Problem

Some example problems with same pattern
- 70. Climbing Stairs 🧠
- 279. Perfect Squares 🧠
- 322. Coin Change 🧠
- 746. Min Cost Climbing Stairs 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
