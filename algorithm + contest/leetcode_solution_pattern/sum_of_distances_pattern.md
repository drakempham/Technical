# Python 🐍 || Prefix Sum + Hash Map 📊 || O(N) Optimization 🚀

## Intuition

When we first look at the problem, a brute force approach comes to mind: find all identical numbers and calculate the distance between their indices one by one. However, this takes `O(N^2)` time and will result in a Time Limit Exceeded (TLE) when the array is large.

The key insight is that the collected indices for any identical number are inherently **sorted in increasing order**. When computing the sum of absolute distances for a point `pos` among a sorted list of points, we can split the distance into two parts:
1.  The distance to all points on its **left** (which are smaller).
2.  The distance to all points on its **right** (which are larger).

Instead of recalculating these distances from scratch using a nested loop, we can keep a running total (**Prefix Sum**) to calculate the distances in `O(1)` time as we iterate through the indices.

## Approach

- **Step 1:** Group the indices of identical numbers using a Hash Map (`defaultdict`). The key is the number, and the value is a list of its indices.
- **Step 2:** Iterate over the grouped indices. For each group, precalculate the `total` sum of indices and the total count `n`.
- **Step 3:** Use a running `prefix_sum` to keep track of the sum of indices to the left of our current position.
- **Step 4:** For each index `pos` at iteration `i` inside the group:
  - **Left Distance:** Since there are `i` elements to the left, their sum is `prefix_sum`. The distance is `(i * pos) - prefix_sum`.
  - **Right Distance:** The sum of elements to the right is `(total - pos - prefix_sum)`. The number of elements to the right is `(n - i - 1)`. The distance is `Right Sum - (Right Count * pos)`.
  - The total distance for this `pos` is simply the sum of Left Distance and Right Distance.
- **Step 5:** Update `prefix_sum` by adding the current `pos` for the next iteration.

## Complexity

- Time complexity: `O(N)`
  We visit each element in `nums` to populate the hash map, and then we process each index exactly once during the prefix sum calculation.
- Space complexity: `O(N)`
  We store the indices in a Hash Map and return an array of size `N`.

## Code

```python
class Solution:
    def distance(self, nums: List[int]) -> List[int]:
        pos_arr = defaultdict(list)
        for i, num in enumerate(nums):
            pos_arr[num].append(i)
            
        ans = [0] * len(nums)
        
        for num, positions in pos_arr.items():
            prefix_sum = 0
            total = sum(positions)
            n = len(positions)
            
            for i, pos in enumerate(positions):
                left_dist = (i * pos) - prefix_sum
                
                # right_elements_sum = total - pos - prefix_sum
                # right_count = n - i - 1
                right_dist = (total - pos - prefix_sum) - (n - i - 1) * pos
                
                ans[pos] = left_dist + right_dist
                prefix_sum += pos
                
        return ans
```

## Why This Works

The formula relies on the absolute difference `|pos - j|`. By splitting the elements into those smaller than `pos` and those larger than `pos`, we remove the need for the absolute value function. The Prefix Sum pattern dynamically maintains the `left_sum` and `right_sum` as we linearly scan the list, transitioning our calculation from an `O(N^2)` quadratic nested loop to a beautiful `O(1)` math operation per element.

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
