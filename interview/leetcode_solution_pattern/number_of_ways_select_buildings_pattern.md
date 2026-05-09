# Count Small Patterns While Scanning || Beginner Friendly ⛳️ || Subsequence DP

## Intuition

We need to count valid groups of 3 buildings.

A valid group must look like one of these patterns:

```text
010
101
```

Instead of checking every triple, we can count smaller pieces while scanning from left to right.

When we see a new character, it can:

- start a new length-1 pattern
- extend an existing length-1 pattern into a length-2 pattern
- extend an existing length-2 pattern into a full valid length-3 pattern

So we only need to remember:

- how many `0`s we have seen
- how many `1`s we have seen
- how many `01` pairs we have seen
- how many `10` pairs we have seen

Then:

- if the current character is `0`, it can finish every previous `01` pair into `010`
- if the current character is `1`, it can finish every previous `10` pair into `101`

This is the main pattern: build the answer by counting useful partial subsequences.

## Approach

- **Step 1:** Keep counters for single characters: `count0`, `count1`.
- **Step 2:** Keep counters for length-2 subsequences: `count01`, `count10`.
- **Step 3:** Keep `ways` for the final answer.
- **Step 4:** Scan the string from left to right.
- **Step 5:** If the current character is `0`, it creates new `10` pairs with all previous `1`s and completes all previous `01` pairs into `010`.
- **Step 6:** If the current character is `1`, it creates new `01` pairs with all previous `0`s and completes all previous `10` pairs into `101`.
- **Step 7:** Return `ways`.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(1)`

## Code

**Python:**

```python
class Solution:
    def numberOfWays(self, s: str) -> int:
        count0 = 0
        count1 = 0
        count01 = 0
        count10 = 0
        ways = 0

        for char in s:
            if char == "0":
                count0 += 1
                count10 += count1
                ways += count01
            else:
                count1 += 1
                count01 += count0
                ways += count10

        return ways
```

## Similar Problem

Some example problems with same pattern

- 2222. Number of Ways to Select Buildings 🧠
- 115. Distinct Subsequences 🧠
- 1930. Unique Length-3 Palindromic Subsequences 🧠
- 2147. Number of Ways to Divide a Long Corridor 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
