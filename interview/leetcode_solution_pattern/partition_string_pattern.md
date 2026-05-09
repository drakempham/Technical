# Split When Duplicate Appears || Beginner Friendly ⛳️ || Greedy + Hash Set

## Intuition

Each substring must contain unique characters.

So while scanning the string, we can keep a set of characters already used in the current substring.

If the next character is not in the set, it can safely stay in the current substring.

But if the character already exists, adding it would make the current substring invalid. At that moment, the best greedy choice is to start a new substring from this character.

Why is this greedy choice safe?

Because the current substring is already as long as it can be without breaking the rule. Delaying the split is impossible, and splitting earlier would only create more substrings.

## Approach

- **Step 1:** Keep a set `seen` for characters inside the current substring.
- **Step 2:** Start with `partitions = 1` because a non-empty string needs at least one substring.
- **Step 3:** Scan each character from left to right.
- **Step 4:** If the character already exists in `seen`, clear `seen` and increase `partitions`.
- **Step 5:** Add the current character to `seen`.
- **Step 6:** Return the number of partitions.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(1)`

Since the string only contains lowercase English letters, the set can hold at most `26` characters.

## Code

**Python:**

```python
class Solution:
    def partitionString(self, s: str) -> int:
        seen = set()
        partitions = 1

        for char in s:
            if char in seen:
                seen.clear()
                partitions += 1

            seen.add(char)

        return partitions
```

## Similar Problem

Some example problems with same pattern

- 3. Longest Substring Without Repeating Characters 🧠
- 763. Partition Labels 🧠
- 1525. Number of Good Ways to Split a String 🧠
- 2405. Optimal Partition of String 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
