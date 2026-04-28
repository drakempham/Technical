# Beginner Friendly ⛳️ || House Robber Pattern 🏠 || Space Optimized DP 🚀

## Intuition

At first glance, this problem looks complex because deleting a number `x` forces you to delete all `x - 1` and `x + 1`. 

However, if we group identical numbers together, we realize that if we choose to take a number `x`, we should take *all* copies of `x` to maximize our points (since doing so doesn't trigger any extra deletions of `x - 1` or `x + 1`). 

Once we sum the total points for each unique number, the problem perfectly transforms into the classic **House Robber** problem! Imagine each number `i` from `1` to `max(nums)` as a house. The "money" inside the house is `count[i] * i`. You cannot rob two adjacent houses (`i` and `i - 1`). What is the maximum money you can rob?

## Why This Works

The maximum points we can get looking at numbers up to `i` only depends on the optimal choices made up to `i - 1` and `i - 2`. 
For any number `i`, we have exactly two choices:
1. **Skip `i`**: We keep the optimal points we had up to `i - 1`.
2. **Take `i`**: We add all points from `i` to the optimal points we had up to `i - 2`.

Because we only ever need to look back at the last two states (`i - 1` and `i - 2`), we don't need a massive array to store all previous answers. We can just use two variables!

## Approach

- **Step 1: Calculate Total Points.** Use a frequency map (or an array) to count how many times each number appears.
- **Step 2: Find the Range.** Find the maximum number in the input array. This tells us how far our "street of houses" goes.
- **Step 3: Setup Variables.** Initialize two variables, `prev1` (represents the max points up to `i - 1`) and `prev2` (represents the max points up to `i - 2`), both to `0`.
- **Step 4: Iterate.** Loop from `1` to `max(nums)`. At each step, calculate the current max score (`curr`) by choosing the better option: taking `i` (`prev2 + count[i] * i`) or skipping `i` (`prev1`).
- **Step 5: Shift Variables.** Move the "window" forward by updating `prev2 = prev1` and `prev1 = curr`. The final answer will be in `prev1`.

## Complexity

- Time complexity: `O(N + M)` where `N` is the number of elements in `nums` and `M` is the maximum value in `nums`. We need `O(N)` to count frequencies and `O(M)` to iterate through the DP.
- Space complexity: `O(N)` in Python (for the Hash Map storing unique numbers) or `O(M)` in Java (for the frequency array up to `maxVal`). The DP itself is perfectly optimized to `O(1)` space!

## Code

**Python:**
```python
from collections import Counter

class Solution:
    def deleteAndEarn(self, nums: list[int]) -> int:
        count = Counter(nums)
        
        prev2 = 0
        prev1 = 0
        
        # Iterate through all possible numbers up to the max value
        for i in range(1, max(nums) + 1):
            # We either take the current number 'i' (adding to prev2)
            # or we skip 'i' (keeping prev1)
            curr = max(prev1, prev2 + count[i] * i)
            
            # Shift variables for the next iteration
            prev2 = prev1
            prev1 = curr
            
        return prev1
```

**Java:**
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Count frequencies. Array index 'i' represents the number 'i'.
        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }
        
        int prev2 = 0;
        int prev1 = 0;
        
        // Iterate through the "houses"
        for (int i = 1; i <= maxVal; i++) {
            int curr = Math.max(prev1, prev2 + count[i] * i);
            
            // Shift variables
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}
```

## Similar Problems

Here are some problems on LeetCode that share this exact same core pattern:
- 198. House Robber 🧠
- 213. House Robber II 🧠

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
