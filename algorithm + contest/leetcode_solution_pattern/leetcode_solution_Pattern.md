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

````md
# [Catchy, readable title with 1-2 relevant emojis]

## Intuition

[Explain the key insight in a simple way.]

## Why This Works

[Beginer-friendly correctness explanation.]

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

**Java:**
```java
[equivalent Java solution code]
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
````

## Multi-Approach Rule

If the problem naturally has multiple useful approaches, generate up to 2 approaches.

Use this format:

- `## Approach 1: ...`
- `## Approach 2: ...`

For each approach, include:

- Intuition
- Why This Works
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
- allowed to include pattern, technique, and difficulty/readability cues (DO NOT include language cues like Python, Java, etc.)

Good examples:

- `# Beginner Friendly ⛳️ || Greedy Approach 🧠 || One-Pass Scan 🚀 ||Clean Bitmask Solution for Maximum Product of Word Lengths 🚀`

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

- always include **both Python and Java** implementations by default
- use clean naming consistent between the two languages
- be formatted for readability
- match the explained approach
- avoid unused variables or dead code
- avoid unnecessary comments inside code
- use idiomatic style for each language (e.g. `Math.abs()` in Java, `abs()` in Python)

Label each block clearly with `**Python:**` and `**Java:**` before the code fence.

If I provide code, improve naming/formatting when useful, but preserve the algorithm unless I ask for optimization.

## Explanation Rules

For the explanation:

- focus on the core invariant or insight
- explain why each major step exists
- connect the idea to the pattern being used
- mention edge cases only when relevant

Do not:

- over-explain obvious syntax
- restate the code line by line
- dump textbook theory unrelated to the solution

## Correctness Rule

Include a short `Why This Works` section.

This section should:

- explain the invariant, greedy choice, DP state meaning, or proof idea
- stay short
- sound natural

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

> Write a LeetCode-ready solution post in Markdown based on the provided problem, idea, or code. Use clean English, a strong title (including both Python 🐍 and Java ☕ in the language segment), beginner-friendly intuition, a clear approach section, correct complexity, both Python and Java implementations (labeled separately), and a short correctness explanation. Include at most 2 approaches only if they add real value. Make the final result directly postable on LeetCode.

## Preferred Closing

Use exactly this closing unless I ask for a different one:

`⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀`.

## Optional Add-ons

Only include these if I explicitly ask:

- dry run
- alternative language
- follow-up optimization
- common mistakes section
- interview tips
- visual intuition

## Priority Order

If there is any conflict, prioritize:

1. technical correctness
2. clarity
3. post-readiness
4. conciseness
5. style
