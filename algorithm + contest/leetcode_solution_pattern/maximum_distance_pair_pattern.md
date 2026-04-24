# Python 🐍 || Two Pointers 👉👈 || O(N) Greedy Approach 🚀

## Intuition

We are given two arrays sorted in **non-increasing** (descending) order. We want to find a pair of indices `(i, j)` such that `i <= j` and `nums1[i] <= nums2[j]`, maximizing the distance `j - i`. 

Because both arrays are sorted in the same descending order, we can use a greedy **Two-Pointer** approach. We start both pointers at the beginning of the arrays. Since we want to maximize `j - i`, whenever we find a valid pair, we try to push the right pointer `j` as far right as possible. If the current pair is invalid, we are forced to move the left pointer `i` forward to find a smaller value in `nums1`.

## Why This Works

This greedy two-pointer logic works perfectly because of the descending sorted property. When `nums1[left] <= nums2[right]`, we are guaranteed that moving `left` forward wouldn't give us a *larger* distance (it shrinks `right - left`), so we only move `right` to explore better distances. Conversely, when `nums1[left] > nums2[right]`, `nums2[right]` is too small. Moving `right` will only yield smaller or equal values in `nums2`. Therefore, the only way to potentially fix the inequality is to move `left` forward to find a smaller `nums1[left]`.

## Approach

- **Step 1:** Initialize two pointers, `left` (for `nums1`) and `right` (for `nums2`), both at index `0`. Initialize `ans` to `0`.
- **Step 2:** Loop while `left < len(nums1)` and `right < len(nums2)`.
- **Step 3:** At each step, check the condition `nums1[left] <= nums2[right]`:
  - **If True (Valid Pair):** The condition is satisfied. If `left <= right`, we calculate the distance `right - left` and update our maximum `ans`. Then, we eagerly increment `right` to see if we can get an even larger distance with the same `left`.
  - **If False (Invalid Pair):** This means `nums1[left]` is too big. Since `nums2` is descending, moving `right` further will only point to even smaller numbers, making it impossible to satisfy the condition. Thus, our only choice is to increment `left` to point to a smaller number in `nums1`.
- **Step 4:** Return `ans`.

## Complexity

- Time complexity: `O(N + M)`
  Both pointers `left` and `right` only move forward. They traverse their respective arrays at most once. `N` is the length of `nums1` and `M` is the length of `nums2`.
- Space complexity: `O(1)`
  We only use a few integer variables (`left`, `right`, `ans`), requiring constant extra space.

## Code

```python
class MaximumDistanceBetweenAPairOfValues:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        left, right = 0, 0
        ans = 0
        while left < len(nums1) and right < len(nums2):
            if nums1[left] <= nums2[right]:
                if left <= right:
                    ans = max(ans, right - left)
                right += 1
            else:
                left += 1
        return ans
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
