# Monotonic Scan 📊 || Beginner Friendly ⛳️ || Two-Pass Solution 🚀

## Intuition

To determine if an element is strictly greater than *all* elements to its left, we don't need to look at every single left-side element individually. We just need to know if it is greater than the **maximum** element seen so far. If it beats the current maximum, it beats everything else too! 

The same logic applies to the right side: if an element is strictly greater than the maximum element seen from the right, it is greater than all elements to its right.

## Why This Works

Think of it like looking at a mountain range from the left side and then from the right side.
- Walking from **left to right**, if a peak is taller than any peak you've seen previously, it stands out (it's valid).
- Walking from **right to left**, if a peak is taller than any peak you've seen previously from that direction, it also stands out (it's valid).
Any peak that stands out from *either* direction satisfies our conditions. By sweeping through the array twice and keeping track of the highest peak seen so far, we can instantly answer the question for any position.

## Approach

- **Step 1: Setup.** Create a boolean array `is_valid` of the same length to mark our valid elements. The first and last elements are unconditionally valid, so mark them as `True`.
- **Step 2: Left-to-Right Scan.** Start tracking a `curr_max` initialized to the first element. Loop forwards. If the current element is strictly greater than `curr_max`, it's valid from the left side! We then update `curr_max` to this new value. 
- **Step 3: Right-to-Left Scan.** Now do the exact same thing but backwards. Start tracking a new `curr_max` initialized to the last element. Loop backwards. If the current element is strictly greater than `curr_max`, it's valid from the right side. Update `curr_max` accordingly.
- **Step 4: Collect Results.** Loop through the array one final time and collect all elements where `is_valid` is `True`.

## Complexity

- **Time complexity:** `O(N)` where `N` is the length of the array. We scan the array fully left-to-right, then right-to-left, and then one final time to collect results.
- **Space complexity:** `O(N)` for our `is_valid` boolean array and the returned results array.

## Code

**Python:**
```python
from typing import List

class Solution:
    def findValidElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        if n <= 2:
            return nums

        is_valid = [False] * n
        is_valid[0] = is_valid[n - 1] = True
        
        # Pass 1: Scan Left to Right
        curr_max = nums[0]
        for i in range(1, n - 1):
            if nums[i] > curr_max:
                is_valid[i] = True
                curr_max = nums[i]  # Update running max

        # Pass 2: Scan Right to Left
        curr_max = nums[n - 1]
        for i in range(n - 2, 0, -1):
            if nums[i] > curr_max:
                is_valid[i] = True
                curr_max = nums[i]  # Update running max

        # Collect all elements marked as valid
        return [nums[i] for i in range(n) if is_valid[i]]
```

**Java:**
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        
        if (n <= 2) {
            for (int num : nums) {
                result.add(num);
            }
            return result;
        }

        boolean[] isValid = new boolean[n];
        isValid[0] = true;
        isValid[n - 1] = true;
        
        // Pass 1: Scan Left to Right
        int currMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > currMax) {
                isValid[i] = true;
                currMax = nums[i]; // Update running max
            }
        }

        // Pass 2: Scan Right to Left
        currMax = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            if (nums[i] > currMax) {
                isValid[i] = true;
                currMax = nums[i]; // Update running max
            }
        }

        // Collect all elements marked as valid
        for (int i = 0; i < n; i++) {
            if (isValid[i]) {
                result.add(nums[i]);
            }
        }

        return result;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
