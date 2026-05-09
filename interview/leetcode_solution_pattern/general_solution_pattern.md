# LeetCode Solution Post Pattern

Use this file as the default instruction set whenever I ask you to generate a LeetCode-style solution post for a real problem or a mock problem.

## Goal

Generate a polished Markdown file in same directory that is ready to publish as a LeetCode solution write-up.

The post should:

- explain the reasoning clearly
- feel beginner-friendly but still technically correct
- include clean code
- include enough structure to be readable on LeetCode
- avoid sounding too generic or AI-generated

## Default Use Case

When I say things like:

- `gen pattern cho bai nay`
- `viet leetcode solution cho bai gia`
- `generate leetcode post`
- `viet bai dang leetcode tu problem nay`

you should follow this document by default, even if I do not repeat these rules.

## Expected Input

I may provide one or more of the following:

- problem statement
- examples
- constraints
- my solution idea
- my code
- a fake problem created for practice
- the algorithm pattern I want to highlight

If some information is missing, make reasonable assumptions and continue.
Only ask for clarification if the missing detail would change the algorithm materially.

## Main Writing Rules

The generated post must:

- be written in clear English
- use Markdown
- be ready to paste into a LeetCode post
- stay concise, but not too thin
- be logically structured
- avoid unnecessary fluff
- avoid claiming an approach is optimal unless it really is

## Output Structure

Always use this structure unless I explicitly ask for a different format.
dp[1][1]

````md
# [Creative, readable title with the main pattern and optional performance stats]

## Intuition

[Explain the key insight in a simple way.]

## Approach

- **Step 1:** ...
- **Step 2:** ...
- **Step 3:** ...

## Complexity

- Time complexity: `O(...)`
- Space complexity: `O(...)`

## Code

**Python:**

```python
[clean Python solution code]
```

## Similar Problem

Some example problems with same pattern

- [problem number]. [problem title] 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
````

## Multi-Approach Rule

If the problem naturally has multiple useful approaches, generate up to 2 approaches.

If my provided code includes multiple approaches, include them in the post when they are meaningful. This includes:

- commented brute force code followed by optimized code
- two working implementations with different complexity
- code comments that explicitly describe an alternative approach

In this case, do not hide the earlier approach only inside the intuition. Write it as `Approach 1`, then explain the improved version as `Approach 2`.

Use this format:

- `## Approach 1: ...`
- `## Approach 2: ...`

For each approach, include:

- Intuition
- Approach
- Complexity
- Code

Only include a second approach if it adds real value, such as:

- brute force to optimized
- sorting to heap
- recursion to DP
- extra space to constant space
- straightforward to pattern-based solution

Do not add a second approach just to make the answer longer.

## Title Style

The title should be:

- prioritizing beginner-friendly and easy-to-understand phrasing
- specific
- easy to read
- mildly catchy
- not clickbait
- optionally styled as short highlight segments separated by `||`
- not fixed to one template; make it feel fresh for each solution
- allowed to be funny, playful, or slightly random if it still clearly describes the solution
- include the main pattern, such as `Grid Dynamic Programming`, `Heap`, `Sliding Window`, or `Prefix Matching`
- include runtime or memory stats only when I provide them
- if I provide stats, put them naturally in the title, such as `O(n*k), beats 90% memory`
- `Beginner Friendly ⛳️` can appear anywhere in the title, not necessarily at the beginning

Good examples:

- `# O(n*k), beats 90% memory || Beginner Friendly ⛳️ || Grid Dynamic Programming || Budget State`
- `# Tiny Heap, Big Answer || Beginner Friendly ⛳️ || Kth Largest Element`
- `# The Prefix Puzzle Solver || Beginner Friendly ⛳️ || Dynamic Programming`

Avoid:

- vague titles
- too many emojis
- all caps
- exaggerated claims like `BEST EVER`

## Tone

The writing tone should be:

- confident
- friendly
- practical
- not overly academic

Avoid:

- robotic explanations
- repeated filler like `In this problem, we need to...`
- overly dramatic phrasing
- empty praise for the approach

## Code Rules

Code must:

- use clean, consistent naming throughout the code
- be formatted for readability
- match the explained approach
- avoid unused variables or dead code
- avoid unnecessary comments inside code
- use idiomatic Python style (`abs()`, list comprehensions when readable, clear variable names)

Label each block clearly with `**Python:**` before the code fence.

If I provide code, improve naming/formatting when useful, but preserve the algorithm unless I ask for optimization.

## Explanation Rules

For the explanation:

- be beginner friendly, aiming to help beginners understand the core idea easily
- avoid a robotic or overly generic tone
- focus on the core invariant or insight
- explain why each major step exists
- connect the idea to the pattern being used
- mention edge cases only when relevant

Do not:

- over-explain obvious syntax
- restate the code line by line
- dump textbook theory unrelated to the solution

## Complexity Rule

Complexity must:

- match the actual implementation
- use standard Big-O notation
- be justified implicitly by the approach

If there are hidden costs from sorting, heap operations, recursion stack, hash maps, or bitmask creation, account for them correctly.

## Mock Problem Rule

If the problem is fake or practice-only:

- still write as if it were a real LeetCode problem
- do not mention that it is fake unless I explicitly ask
- infer a realistic title from the described task
- produce a post that looks publishable

## Pattern Emphasis Rule

If I mention a pattern such as:

- sliding window
- two pointers
- monotonic stack
- backtracking
- DFS / BFS
- union find
- prefix sum
- bitmask
- dynamic programming

then make that pattern visible in:

- the title when appropriate
- the intuition
- the approach section

But do not force pattern terminology if it makes the writing awkward.

## Quality Checklist

Before finalizing, ensure the post:

- has a clear title
- has no broken Markdown
- has correct complexity
- has code consistent with the explanation
- is concise enough to read comfortably
- is polished enough to paste directly into LeetCode

## Default Prompt Contract

When generating from this file, assume the instruction below:

> Write a LeetCode-ready solution post in Markdown based on the provided problem, idea, or code. Use clean English, a creative and readable title that includes the main pattern and optional runtime or memory stats when I provide them. The title does not need to follow a fixed template, but it should still be clear, beginner-friendly, and not clickbait. Include beginner-friendly intuition, a clear approach section, correct complexity, a clean Python implementation, and a short correctness explanation. Include at most 2 approaches only if they add real value. Make the final result directly postable on LeetCode.

## Similar Problem

Find some similar problems with the same pattern from LeetCode and put their titles here.

Use this format:

```
Some example problems with same pattern
- 198. House Robber 🧠
- 213. House Robber II 🧠
- 740. Delete and Earn 🧠
```

## Preferred Closing

Use exactly this closing unless I ask for a different one:

`⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀`.

<!--
## Optional Add-ons

Only include these if I explicitly ask:
- A question to discuss at the end, with this title: "💡 Thought for 1 min:" - a question not in markdown style
-->

## Priority Order

If there is any conflict, prioritize:

1. technical correctness
2. clarity
3. post-readiness
4. conciseness
5. style
