# 4 Ways to Trap Rain Water || Beginner Friendly ⛳️ || Two Pointers, DP, Stack

## Intuition

The "Trapping Rain Water" problem is a classic algorithm question that fundamentally asks: *How much water can sit above each bar?* The key insight is that the water above any given bar is bounded by the highest bar to its left and the highest bar to its right. Specifically, the water level at index `i` is exactly `min(max_left, max_right) - height[i]`. We can solve this using multiple patterns, evolving from a simple brute force to an optimal two-pointer approach!

## Approach 1: Brute Force

### Intuition
For every bar, we can simply look at all the bars to its left to find the maximum height, and look at all bars to its right to find the maximum height.

### Why This Works
The logic follows the core principle directly: `water_at_i = min(highest_left, highest_right) - height[i]`. By manually scanning left and right for every single element, we guarantee we find the correct bounds.

### Approach
- **Step 1:** Iterate through each bar in the array from index `1` to `n-1`.
- **Step 2:** For each bar, run a loop to the left to find `max_left`.
- **Step 3:** For each bar, run a loop to the right to find `max_right`.
- **Step 4:** Add `min(max_left, max_right) - height[i]` to our total answer.

### Complexity
- Time complexity: `O(n^2)` because for each of the `n` elements, we scan up to `n` elements left and right.
- Space complexity: `O(1)` as we only use a few variables.

### Code

**Python:**

```python
from typing import List

class TrappingWater:
    def trap(self, height: List[int]) -> int:
        ans = 0
        for i in range(1, len(height)):
            max_left, max_right = 0, 0
            for j in range(i, -1, -1):
                max_left = max(max_left, height[j])
            for j in range(i, len(height), 1):
                max_right = max(max_right, height[j])
            ans += min(max_left, max_right) - height[i]
        return ans
```

## Approach 2: Prefix and Suffix Max Arrays (Dynamic Programming)

### Intuition
In the brute force approach, we repeatedly scan the array to find `max_left` and `max_right`. Instead, we can precompute these values!

### Why This Works
By creating a prefix array for the maximums from the left, and a suffix array for the maximums from the right, we trade a little bit of space for a huge time improvement. We can now look up the bounds for any bar in `O(1)` time.

### Approach
- **Step 1:** Create `left_max` and `right_max` arrays of the same size as `height`.
- **Step 2:** Fill `left_max` by iterating left-to-right, maintaining the maximum seen so far.
- **Step 3:** Fill `right_max` by iterating right-to-left, maintaining the maximum seen so far.
- **Step 4:** Iterate through the array one last time, calculating trapped water as `min(left_max[i], right_max[i]) - height[i]`.

### Complexity
- Time complexity: `O(n)` because we do 3 separate passes over the array.
- Space complexity: `O(n)` to store the `left_max` and `right_max` arrays.

### Code

**Python:**

```python
class TrappingWater:
    def trap(self, height: List[int]) -> int:
        if len(height) == 0:
            return 0
        ans = 0
        size = len(height)
        
        left_max, right_max = [0] * size, [0] * size
        
        # Initialize first height into left max
        left_max[0] = height[0]
        for i in range(1, size):
            # update left max with current max
            left_max[i] = max(height[i], left_max[i - 1])
            
        # Initialize last height into right max
        right_max[size - 1] = height[size - 1]
        for i in range(size - 2, -1, -1):
            # update right max with current max
            right_max[i] = max(height[i], right_max[i + 1])
            
        # Calculate the trapped water
        for i in range(1, size - 1):
            ans += min(left_max[i], right_max[i]) - height[i]
            
        # Return amount of trapped water
        return ans
```

## Approach 3: Monotonic Stack

### Intuition
We can also look at the problem horizontally instead of vertically. By using a monotonic decreasing stack, we can keep track of "valleys" and calculate water bounded by higher walls on both sides.

### Why This Works
The stack stores indices of bars in decreasing order of height. When we encounter a bar taller than the top of our stack, it means we've found the right boundary of a valley. The new top of the stack will be the left boundary. We can then calculate the water trapped between them.

### Approach
- **Step 1:** Initialize an empty stack and iterate through the array.
- **Step 2:** If the current bar is taller than the bar at the top of the stack, pop the stack. This popped bar is the bottom of the valley.
- **Step 3:** If the stack is now empty, there's no left boundary, so break.
- **Step 4:** Otherwise, the new stack top is the left boundary. The width is `curr - stack[-1] - 1`, and the water height is `min(height[curr], height[stack[-1]]) - height[bottom]`. Add `width * height` to total.
- **Step 5:** Push the current index to the stack.

### Complexity
- Time complexity: `O(n)` because each element is pushed and popped from the stack at most once.
- Space complexity: `O(n)` for the stack in the worst-case (a monotonically decreasing array).

### Code

**Python:**

```python
class TrappingWater:
    def trap(self, height: List[int]) -> int:
        ans = 0
        stack = []
        curr = 0
        while curr < len(height):
            while len(stack) > 0 and height[curr] > height[stack[-1]]:
                bottom_height = height[stack.pop()]
                if len(stack) == 0:
                    break
                curr_width = curr - stack[-1] - 1
                curr_height = min(height[curr], height[stack[-1]]) - bottom_height
                ans += curr_width * curr_height
            stack.append(curr)
            curr += 1
        return ans
```

## Approach 4: Two Pointers (Optimal)

### Intuition
Can we achieve `O(1)` space without scanning `O(n^2)` times? Yes! We don't actually need to know *both* maximums perfectly. We only care about the *minimum* of the two maximums. Two pointers from opposite ends can help us decide which side is the limiting factor!

### Why This Works
If `height[left] < height[right]`, then we know that `left_max` will definitely be smaller than `right_max` (or at most equal). The right side is a giant wall that guarantees our water level is determined strictly by the `left_max`. We can safely calculate water on the left, update `left_max`, and move the left pointer inward.

### Approach
- **Step 1:** Initialize `left = 0` and `right = len(height) - 1`.
- **Step 2:** Maintain `left_max` and `right_max` variables.
- **Step 3:** While `left < right`, compare `height[left]` and `height[right]`.
- **Step 4:** If `height[left] < height[right]`, update `left_max` and add `left_max - height[left]` to total. Move `left` pointer.
- **Step 5:** Else, update `right_max`, add `right_max - height[right]`, and move `right` pointer.

### Complexity
- Time complexity: `O(n)` because we process each element exactly once using the two pointers.
- Space complexity: `O(1)` since we only use a few variables to track maximums and pointers.

### Code

**Python:**

```python
class TrappingWater:
    def trap(self, height: List[int]) -> int:
        left, right = 0, len(height) - 1
        res = 0
        max_in_left, max_in_right = 0, 0
        while left <= right:
            if height[left] < height[right]:
                max_in_left = max(max_in_left, height[left])
                res += max_in_left - height[left]
                left += 1
            else:
                max_in_right = max(max_in_right, height[right])
                res += max_in_right - height[right]
                right -= 1
        return res
```

## Similar Problem

Some example problems with same pattern
- 11. Container With Most Water 🧠
- 84. Largest Rectangle in Histogram 🧠
- 407. Trapping Rain Water II 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
class Solution:
    def maximumAmount(self, coins: List[List[int]]) -> int:
        
        