# Separate First, Sort Only What Matters || Beginner Friendly ⛳️ || Custom Sorting

## Intuition

There are two different kinds of logs:

- letter-logs
- digit-logs

They do not follow the same rule.

Letter-logs must be sorted by their content first. If two letter-logs have the same content, then we sort by their identifier.

Digit-logs are easier: their relative order must stay exactly the same as the input.

So the clean pattern is:

First separate the logs, then sort only the letter-logs, then append the digit-logs back.

This avoids trying to force both log types into one complicated sorting rule.

## Approach

- **Step 1:** Create two lists: `letter_logs` and `digit_logs`.
- **Step 2:** For each log, split it into identifier and content using `split(" ", 1)`.
- **Step 3:** If the first character of the content is a letter, put the log into `letter_logs`.
- **Step 4:** Otherwise, put the log into `digit_logs`.
- **Step 5:** Sort `letter_logs` by `(content, identifier)`.
- **Step 6:** Return `letter_logs + digit_logs`.

## Complexity

- Time complexity: `O(n log n * m)`
- Space complexity: `O(n)`

Here, `n` is the number of logs, and `m` is the average log length used during comparison/splitting.

## Code

**Python:**

```python
from typing import List


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letter_logs = []
        digit_logs = []

        for log in logs:
            identifier, content = log.split(" ", 1)

            if content[0].isalpha():
                letter_logs.append(log)
            else:
                digit_logs.append(log)

        letter_logs.sort(key=lambda log: (
            log.split(" ", 1)[1],
            log.split(" ", 1)[0]
        ))

        return letter_logs + digit_logs
```

## Similar Problem

Some example problems with same pattern

- 937. Reorder Data in Log Files 🧠
- 179. Largest Number 🧠
- 56. Merge Intervals 🧠
- 252. Meeting Rooms 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
