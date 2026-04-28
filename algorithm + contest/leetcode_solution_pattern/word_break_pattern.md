# Beginner Friendly ⛳️ || Python & Java || Dynamic Programming  || Prefix Matching 

## Intuition

Imagine you have a long, unspaced sentence and a dictionary of valid words. How do you know if the whole sentence can be broken into valid words? Instead of trying every possible combination (which takes way too long), we can build our answer step-by-step from the beginning of the string. If we know that the first part of the string can be broken into words, and the remaining part matches a word in our dictionary, then the whole string up to this point is valid!

## Why This Works

This is a classic Dynamic Programming (DP) approach. We maintain a memory (an array called `dp`) where `dp[i]` tells us whether the prefix of the string of length `i` can be perfectly segmented into dictionary words. For any length `i`, we look back: if the string was perfectly segmented at some earlier point (`i - len(word)`), and the text between that point and `i` perfectly matches `word`, then we know the string up to length `i` is also perfectly segmentable. 

By building this up from length `1` to `n`, we guarantee that we only build upon valid, previously verified prefixes.

## Approach

- **Step 1:** Create a boolean `dp` array of size `n + 1`, where `n` is the length of string `s`. Initialize all elements to `False`.
- **Step 2:** Set `dp[0] = True`. This is our base case, meaning an empty string is always valid and requires zero dictionary words.
- **Step 3:** Loop through the string length from `1` to `n` using index `i`. This `i` represents the length of the prefix we are currently checking.
- **Step 4:** For each length `i`, loop through every `word` in the `wordDict`. Check if the current prefix length `i` is at least the length of the word (`i >= len(word)`). 
- **Step 5:** Check if the prefix ending just before this word was valid (`dp[i - len(word)]`). If it was, and the substring of `s` ending at `i` exactly matches the `word`, then we mark `dp[i] = True` and instantly `break` out of the inner loop (since we just need one valid way to form the string up to `i`).
- **Step 6:** Return `dp[n]`, which represents whether the entire string of length `n` can be completely segmented.

## Complexity

- Time complexity: `O(n \times m \times L)` where `n` is the length of string `s`, `m` is the number of words in `wordDict`, and `L` is the maximum length of a word in the dictionary. We loop through all `n` lengths, check all `m` words, and performing the substring comparison takes `O(L)` time.
- Space complexity: `O(n)` because we use an array of size `n + 1` to store our DP states.

## Code

**Python:**
```python
from typing import List

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        n = len(s)
        dp = [False] * (n + 1)
        dp[0] = True  # Base case: empty string is valid
        
        for i in range(1, n + 1):
            for word in wordDict:
                # If we have enough characters to form the word
                # AND the prefix before this word was valid
                # AND the current substring matches the word
                if i >= len(word) and dp[i - len(word)] and s[i - len(word): i] == word:
                    dp[i] = True
                    break  # Found a valid split ending at i, no need to check other words
        
        return dp[n]
```

**Java:**
```java
import java.util.List;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case
        
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                // Check if length is sufficient, previous prefix is valid, and substring matches
                if (i >= len && dp[i - len] && s.substring(i - len, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}
```

## Similar problem

Some example problems with same pattern
- 140. Word Break II 🧠
- 472. Concatenated Words 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
