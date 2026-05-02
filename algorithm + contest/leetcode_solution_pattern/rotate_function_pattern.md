# One Spin, One Formula || Beginner Friendly ⛳️ || Math + Prefix Thinking

## Approach 1: Brute Force Simulation

### Intuition

The brute force way is easy to understand: rotate the array every possible way, calculate the rotate function each time, and keep the maximum.

For each rotation, we try every position `j`, find which number should appear there after rotation, then add:

`j * nums[rotated_index]`

This is very direct, but it recalculates the whole rotate function for every rotation.

### Why This Works

For every possible rotation, we compute exactly what the problem asks for: each value multiplied by its new index. Since we check all `n` rotations and keep the maximum value, the final answer must be correct.

### Approach

- **Step 1:** Initialize `answer` as negative infinity.
- **Step 2:** Try every rotation from `0` to `n - 1`.
- **Step 3:** For each rotation, compute the rotate function by checking every index.
- **Step 4:** Update `answer` with the best value.
- **Step 5:** Return `answer`.

### Complexity

- Time complexity: `O(n^2)`, because we compute `n` values for each of the `n` rotations.
- Space complexity: `O(1)`, because we only use a few variables.

### Code

**Python:**

```python
from typing import List


class Solution:
    def maxRotateFunction(self, nums: List[int]) -> int:
        n = len(nums)
        answer = float("-inf")

        for rotation in range(n):
            current = 0
            for index in range(n):
                rotated_index = (index + rotation) % n
                current += index * nums[rotated_index]

            answer = max(answer, current)

        return answer
```

## Approach 2: Math Formula Optimization

### Intuition

The brute force approach works, but it costs `O(n^2)` because every rotation recomputes the whole formula from scratch.

The key observation is that two neighboring rotations are closely related. We do not need to rebuild the full value every time.

Let:

`F(0) = 0 * nums[0] + 1 * nums[1] + ... + (n - 1) * nums[n - 1]`

When we rotate once, every element's index increases by `1`, except the last element, which moves back to index `0`.

So most elements gain `+nums[i]`, but the moved element loses `n * nums[last]`.

That gives us the formula:

`next_f = current_f + total_sum - n * moved_value`

### Why This Works

Each rotation changes the contribution of every number in a predictable way.

For a normal element that shifts one position to the right, its multiplier increases by `1`, so the total value increases by that element's value.

If we added this for all elements, we would add `total_sum`. But the element that wraps around from the end to the front should not gain `+1`; it goes from multiplier `n - 1` back to `0`. That means we added too much for this element, so we subtract `n * moved_value`.

This gives the correct value for the next rotation in `O(1)` time.

### Approach

- **Step 1:** Let `n` be the length of `nums`.
- **Step 2:** Calculate `total`, the sum of all numbers.
- **Step 3:** Calculate `F(0)` directly using `sum(i * nums[i])`.
- **Step 4:** Initialize `answer = F(0)`.
- **Step 5:** For each next rotation, update the current value using:

  `f = f + total - n * nums[n - i]`

- **Step 6:** Update the answer with the maximum value seen so far.
- **Step 7:** Return `answer`.

### Complexity

- Time complexity: `O(n)`, because we calculate the first value once and then update each rotation in constant time.
- Space complexity: `O(1)`, because we only store a few variables.

### Code

**Python:**

```python
from typing import List


class Solution:
    def maxRotateFunction(self, nums: List[int]) -> int:
        n = len(nums)
        total = sum(nums)

        current = sum(i * nums[i] for i in range(n))
        answer = current

        for rotation in range(1, n):
            moved_value = nums[n - rotation]
            current = current + total - n * moved_value
            answer = max(answer, current)

        return answer
```

## Similar Problem

Some example problems with same pattern

- 238. Product of Array Except Self 🧠
- 413. Arithmetic Slices 🧠
- 724. Find Pivot Index 🧠
- 1423. Maximum Points You Can Obtain from Cards 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
