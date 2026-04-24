# Python 🐍 Java ☕ || Fisher-Yates 🔀 || Array Shuffling || Shuffle an Array 🎲

## Intuition

The core challenge of this problem is to shuffle an array such that every possible permutation is equally likely. A naive approach of randomly picking indices might introduce bias or require extra space and retries for duplicates. The most efficient and standard way to achieve a perfectly uniform shuffle in-place is using the **Fisher-Yates (or Knuth) Shuffle** algorithm.

## Why This Works

The Fisher-Yates shuffle algorithm builds the randomized array one element at a time. At each index `i`, we pick a random element from the remaining unshuffled portion of the array (from index `i` to `n-1`) and swap it into the current position `i`. Since each element has an exact, equal probability of being placed at each index, the mathematical result is a perfectly uniform distribution of permutations.

## Approach

- **Step 1:** In the constructor, store a copy of the input array. This serves as our immutable reference for the `reset()` function.
- **Step 2:** For the `reset()` function, simply return a fresh copy of the original array.
- **Step 3:** For the `shuffle()` function, first create a working copy of the original array.
- **Step 4:** Iterate through the array from `i = 0` to `n-2`. At each step, generate a random index `j` such that `i <= j < n`.
- **Step 5:** Swap the elements at indices `i` and `j`, then return the fully shuffled array.

## Complexity

- Time complexity: `O(N)` for both `reset()` and `shuffle()`, where `N` is the number of elements in the array. Copying the array takes linear time, and the Fisher-Yates shuffle also runs in exactly `N` steps.
- Space complexity: `O(N)` to store the `origin` array state and to create the temporary `copy_arr` returned during function calls.

## Code

**Python:**
```python
from typing import List
from random import randint

class Solution:

    def __init__(self, nums: List[int]):
        self.origin = nums[:]

    def reset(self) -> List[int]:
        return self.origin[:]

    def shuffle(self) -> List[int]:
        n = len(self.origin)
        copy_arr = self.origin[:]
        
        for i in range(0, n - 1):
            # Pick a random index between i and n-1 (inclusive)
            j = randint(i, n - 1)
            # Swap
            copy_arr[i], copy_arr[j] = copy_arr[j], copy_arr[i]
            
        return copy_arr
```

**Java:**
```java
import java.util.Random;

class Solution {
    private int[] origin;
    private Random random;

    public Solution(int[] nums) {
        // Store a copy to preserve the original state
        this.origin = nums.clone();
        this.random = new Random();
    }
    
    public int[] reset() {
        return this.origin.clone();
    }
    
    public int[] shuffle() {
        int[] copyArr = this.origin.clone();
        int n = copyArr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Pick a random index between i and n-1 (inclusive)
            int j = i + random.nextInt(n - i);
            // Swap
            int temp = copyArr[i];
            copyArr[i] = copyArr[j];
            copyArr[j] = temp;
        }
        
        return copyArr;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
