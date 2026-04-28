# DP + Fenwick Tree 🌲 || Delayed Update ⏰ || Beginner Friendly ⛳️

## Intuition

This problem asks us to find the maximum sum of an alternating (zig-zag) subsequence where adjacent chosen elements are separated by at least `k` indices. 

If we use standard Dynamic Programming (DP), for every element `i`, we would have to look back at every element `j` (where `i - j >= k`) and check if it's smaller or larger. That takes $O(N^2)$ time and is too slow.

To speed this up, we need a way to instantly ask: *"Out of all the valid past elements that are strictly smaller (or strictly larger) than my current number, which one gives the biggest running sum?"* 

This is where a **Fenwick Tree (Binary Indexed Tree)** shines! By using a Fenwick Tree to keep track of the maximum sums, we can find the best past element in just $O(\log N)$ time. To enforce the "at least `k` indices apart" rule, we use a **Delayed Update** technique.

## Why This Works

Imagine you are building a rollercoaster track. You want to connect a piece of track that goes UP, so you need to attach it to a piece of track that just went DOWN. But you also have a rule: the new piece must be built at least `k` days after the previous piece.

1. **The Delayed Update (`ready = num - k`)**: 
   Instead of looking through a notebook of all past pieces, you keep a "Ready Board" (the Fenwick Tree). You only pin a piece to the Ready Board when `k` days have passed. When you are at day `num`, the piece from day `num - k` becomes officially ready!
2. **The Fenwick Tree (`MaxKeeper`)**: 
   When you need to build an UP track, you instantly ask the Ready Board: "Give me the best DOWN track that is lower than my current height." The tree hands it to you immediately.

## Approach

- **Step 1: Coordinate Compression.** Because Fenwick Trees work best with small, continuous indices (like $1, 2, 3...$), we sort all unique numbers and assign them a "rank" from $1$ to $W$.
- **Step 2: Initialize Two Trees.** 
  - `low` tree: keeps track of maximum sums for values smaller than the current one.
  - `high` tree: keeps track of maximum sums for values larger than the current one (we reverse the ranks so we can query prefix maximums).
- **Step 3: Process Elements with Delay.** Loop through the array. For the current index `num`:
  - **Unlock phase:** Check if `ready = num - k >= 0`. If yes, that past element is now valid. Put its previously calculated `up` and `down` sums into the respective Fenwick Trees at its rank.
  - **Query phase:** Query the `low` tree for the best sum from elements strictly smaller than `nums[num]`. Add `nums[num]` to it to form an UP step.
  - **Query phase:** Query the `high` tree for the best sum from elements strictly larger than `nums[num]`. Add `nums[num]` to it to form a DOWN step.
- **Step 4:** Keep track of the overall maximum sum `t`.

## Complexity

- **Time complexity:** $O(N \log W)$, where $N$ is the number of elements and $W$ is the number of unique elements. Sorting takes $O(N \log N)$ and each query/update to the Fenwick Tree takes $O(\log W)$.
- **Space complexity:** $O(N)$ to store the DP arrays (`up`, `down`), the Fenwick Trees, and the rank mapping.

## Code

**Python:**
```python
class MaxKeeper:
    def __init__(self, n: int = 0):
        self.n = n
        self.tree = [float("-inf")] * (n + 1)

    def put(self, pos: int, val: int) -> None:
        while pos <= self.n:
            if val > self.tree[pos]:
                self.tree[pos] = val
            pos += pos & -pos

    def take(self, pos: int) -> int:
        res = float("-inf")
        while pos > 0:
            if self.tree[pos] >= res:
                res = self.tree[pos]
            pos -= pos & -pos
        return res

class Solution:
    def maxAlternatingSum(self, nums: list[int], k: int) -> int:
        # Step 1: Coordinate Compression
        ordered = sorted(set(nums))
        order_rank = {v: i + 1 for i, v in enumerate(ordered)}
        width = len(ordered)

        # Step 2: Initialize Trees
        low = MaxKeeper(width)
        high = MaxKeeper(width)

        up = [float("-inf")] * len(nums)
        down = [float("-inf")] * len(nums)

        t = max(nums)

        for num in range(len(nums)):
            ready = num - k

            # Step 3a: Delayed Update (Unlock phase)
            if ready >= 0:
                val = nums[ready]
                pos = order_rank[val]

                low.put(pos, max(val, down[ready]))
                high.put(width - pos + 1, max(val, up[ready]))

            cur_val = nums[num]
            cur_pos = order_rank[cur_val]

            # Step 3b: DP Queries
            prev_l = low.take(cur_pos - 1)
            if prev_l != float("-inf"):
                up[num] = prev_l + cur_val

            prev_h = high.take(width - cur_pos)
            if prev_h != float("-inf"):
                down[num] = prev_h + cur_val

            t = max(t, up[num], down[num])

        return t
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
