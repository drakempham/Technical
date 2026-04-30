# Beginner Friendly ⛳️ || Python & Java || Dynamic Programming || Wildcard Matching

## Intuition

This problem becomes much easier if we compare prefixes instead of trying to match the whole string at once.

Let `dp[i][j]` mean:

`s[0 : i]` matches `p[0 : j]`

Now every decision depends only on smaller prefixes:

- If the current pattern character is a normal character, it must equal the current string character.
- If the current pattern character is `?`, it can match exactly one character.
- If the current pattern character is `*`, it can match either:
  - nothing, so we ignore `*`
  - one more character, so we keep using `*`

That is the main DP pattern here: define a state for two prefixes, then transition from already solved smaller prefix states.

## Why This Works

The invariant is simple: `dp[i][j]` is `True` only when the first `i` characters of `s` can be fully matched by the first `j` characters of `p`.

For a normal character or `?`, both sides must consume one character, so we look at `dp[i - 1][j - 1]`.

For `*`, there are two valid choices. It can match an empty sequence, which gives `dp[i][j - 1]`, or it can match the current character from `s`, which gives `dp[i - 1][j]`. If either choice works, then `dp[i][j]` is also valid.

Since we fill the table from smaller prefixes to larger prefixes, every transition uses states that are already known.

## Approach

- **Step 1:** Let `m = len(s)` and `n = len(p)`.
- **Step 2:** Create a DP table of size `(m + 1) x (n + 1)`, initialized to `False`.
- **Step 3:** Set `dp[0][0] = True`, because an empty string matches an empty pattern.
- **Step 4:** Fill the first row. An empty string can only match a pattern prefix if every character so far is `*`.
- **Step 5:** Loop through every `i` from `1` to `m` and every `j` from `1` to `n`.
- **Step 6:** If `p[j - 1]` is `?` or equals `s[i - 1]`, set `dp[i][j] = dp[i - 1][j - 1]`.
- **Step 7:** If `p[j - 1]` is `*`, set `dp[i][j] = dp[i][j - 1] or dp[i - 1][j]`.
- **Step 8:** Return `dp[m][n]`.

## Complexity

- Time complexity: `O(m * n)`, where `m` is the length of `s` and `n` is the length of `p`.
- Space complexity: `O(m * n)` for the DP table.

## Code

**Python:**
```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True

        for j in range(1, n + 1):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j - 1]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == "?" or p[j - 1] == s[i - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == "*":
                    dp[i][j] = dp[i][j - 1] or dp[i - 1][j]

        return dp[m][n]
```

**Java:**
```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char patternChar = p.charAt(j - 1);
                char stringChar = s.charAt(i - 1);

                if (patternChar == '?' || patternChar == stringChar) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (patternChar == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
