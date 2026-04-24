# Python 🔥 || Greedy Approach 🧠 || One-Pass Scan 🚀 || Increasing Triplet Subsequence ⛳️

## Intuition

We do not need to store an actual triplet.
We only need to know whether we can build one while scanning from left to right.

The key greedy idea is to keep:
- the smallest value seen so far
- the smallest possible second value that is still greater than the first

If we later find a number bigger than both, then an increasing triplet must exist.

## Approach

- **Step 1:** If the array has fewer than 3 elements, return `False` immediately.
- **Step 2:** Track two values, `first_num` and `second_num`, both initialized to infinity.
- **Step 3:** For each number in `nums`:
  - if it is smaller than or equal to `first_num`, update `first_num`
  - otherwise, if it is smaller than or equal to `second_num`, update `second_num`
  - otherwise, it is greater than both tracked values, so we found a valid increasing triplet
- **Step 4:** If the loop ends without finding such a number, return `False`.

## Complexity

- Time complexity: `O(n)`
- Space complexity: `O(1)`

## Code

```python
from typing import List


class Solution:
    def increasingTriplet(self, nums: List[int]) -> bool:
        if len(nums) < 3:
            return False

        first_num = float("inf")
        second_num = float("inf")

        for num in nums:
            if num <= first_num:
                first_num = num
            elif num <= second_num:
                second_num = num
            else:
                return True

        return False
```

## Why This Works

At every step, `first_num` is the smallest candidate for the first element, and `second_num` is the smallest candidate for the second element that can appear after it.
Keeping both as small as possible gives us the best chance to fit a third larger number later.
So if we ever see a value greater than `second_num`, we have found `first_num < second_num < current`, which forms a valid increasing triplet.

---

If this explanation helped, feel free to upvote it.
