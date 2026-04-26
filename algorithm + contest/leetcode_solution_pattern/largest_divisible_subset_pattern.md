# Python 🐍 Java ☕ || Dynamic Programming 🧠 || Parent Reconstruction 🚀 || Largest Divisible Subset

## Intuition

After sorting the array, if `nums[i]` is divisible by `nums[j]` and `j < i`, then `nums[i]` can be placed after the subset ending at `nums[j]`.

This makes the problem very similar to Longest Increasing Subsequence, but instead of checking increasing order, we check divisibility.

The main idea is:

- `dp[i]` stores the length of the largest divisible subset ending at `nums[i]`
- `parent[i]` stores the previous index in that subset, so we can rebuild the answer at the end

## Why This Works

Sorting is important because if `a` divides `b`, then `a <= b`. Once the array is sorted, every valid previous element for `nums[i]` must appear before index `i`.

For each `nums[i]`, we try every earlier `nums[j]`. If `nums[i] % nums[j] == 0`, then the subset ending at `j` can be extended by `nums[i]`. We keep the best such extension.

The `parent` array remembers the best previous element, so after finding the index with the largest `dp` value, we can trace backward and reconstruct the actual subset.

## Approach

- **Step 1:** Sort `nums` in increasing order.
- **Step 2:** Initialize `dp[i] = 1`, because each number alone is a valid subset.
- **Step 3:** Initialize `parent[i] = -1`, meaning the subset currently starts at `nums[i]`.
- **Step 4:** For every pair `(j, i)` where `j < i`, check whether `nums[i] % nums[j] == 0`.
- **Step 5:** If extending the subset ending at `j` gives a better answer for `i`, update `dp[i]` and `parent[i]`.
- **Step 6:** Track the index with the largest subset length.
- **Step 7:** Reconstruct the answer by following `parent` pointers from that best index.

## Complexity

- Time complexity: `O(n^2)`
- Space complexity: `O(n)`

Sorting costs `O(n log n)`, but the nested DP loop costs `O(n^2)`, so the total time complexity is `O(n^2)`.

## Code

**Python:**
```python
from typing import List


class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        if not nums:
            return []

        nums.sort()
        n = len(nums)

        dp = [1] * n
        parent = [-1] * n
        best_index = 0

        for i in range(n):
            for j in range(i):
                if nums[i] % nums[j] == 0 and dp[j] + 1 > dp[i]:
                    dp[i] = dp[j] + 1
                    parent[i] = j

            if dp[i] > dp[best_index]:
                best_index = i

        answer = []
        while best_index != -1:
            answer.append(nums[best_index])
            best_index = parent[best_index]

        return answer[::-1]
```

**Java:**
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);

        int bestIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (dp[i] > dp[bestIndex]) {
                bestIndex = i;
            }
        }

        List<Integer> answer = new ArrayList<>();
        while (bestIndex != -1) {
            answer.add(nums[bestIndex]);
            bestIndex = parent[bestIndex];
        }

        Collections.reverse(answer);
        return answer;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
