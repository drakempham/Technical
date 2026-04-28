# Beginner Friendly ⛳️ || Python & Java || Dynamic Programming || Skip or Solve

## Intuition

For every question, we have only two choices:

- **Skip it** and move to the next question.
- **Solve it**, gain its points, then skip the next `brainpower` questions.

This is a perfect Dynamic Programming problem because the best answer starting from question `i` depends on answers from future positions. Instead of trying every possible path recursively, we store the best score we can get from each index.

The key trick is to process the questions from right to left. That way, when we are deciding what to do at question `i`, the future answers are already known.

## Why This Works

Let `dp[i]` mean the maximum points we can earn starting from question `i`.

At each question `i`, we compare two choices:

1. **Skip question `i`**: then our score is `dp[i + 1]`.
2. **Solve question `i`**: then our score is `points[i] + dp[i + brainpower[i] + 1]`.

Since those are the only two possible decisions, taking the maximum of them gives the best answer for `dp[i]`. By filling the array from right to left, every future state we need has already been calculated.

## Approach

- **Step 1:** Let `n` be the number of questions.
- **Step 2:** Create a `dp` array of size `n + 1`, initialized with `0`.
- **Step 3:** Traverse questions from right to left.
- **Step 4:** For each question, calculate the score if we solve it:
  - add its points
  - jump to `i + brainpower + 1`
  - add `dp[nextIndex]` if that index is still inside the array
- **Step 5:** Compare solving with skipping: `dp[i] = max(solve, dp[i + 1])`.
- **Step 6:** Return `dp[0]`, the maximum points from the start.

## Complexity

- Time complexity: `O(n)` because we process each question exactly once.
- Space complexity: `O(n)` because we store one DP value for each index.

## Code

**Python:**
```python
from typing import List

class Solution:
    def mostPoints(self, questions: List[List[int]]) -> int:
        n = len(questions)
        dp = [0] * (n + 1)

        for i in range(n - 1, -1, -1):
            points, brainpower = questions[i]
            next_question = i + brainpower + 1

            solve = points
            if next_question < n:
                solve += dp[next_question]

            skip = dp[i + 1]
            dp[i] = max(solve, skip)

        return dp[0]
```

**Java:**
```java
class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int brainpower = questions[i][1];
            int nextQuestion = i + brainpower + 1;

            long solve = points;
            if (nextQuestion < n) {
                solve += dp[nextQuestion];
            }

            long skip = dp[i + 1];
            dp[i] = Math.max(solve, skip);
        }

        return dp[0];
    }
}
```

## Similar Problems

Some example problems with the same pattern:

- 198. House Robber
- 740. Delete and Earn
- 1235. Maximum Profit in Job Scheduling

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
