# Iterative Post-Order Traversal 🌲 || Beginner Friendly ⛳️ || Bottom-Up Approach 🚀

## Intuition

When dealing with a tree, a great way to solve partitioning problems is to work from the leaves up to the root. If we look at a leaf node (or a subtree), we can easily decide if it can form a valid component: if the sum of its values is divisible by `k`, we can safely "cut" the edge connecting it to its parent. If it's not divisible by `k`, we have no choice but to pass its remaining sum up to its parent to see if combining them will eventually be divisible by `k`.

## Why This Works

Think of it like gathering coins to make exact change. You start at the outermost branches (the leaves). 
- If a branch has exactly enough coins to be divided evenly by `k`, you package it up and cut it off (a valid component!). 
- If it has some leftover remainder, it can't survive on its own, so it gives its leftovers to its parent.
Because we process everything from the bottom up, by the time we evaluate a parent node, it has already collected all the necessary leftovers from its children.

## Approach

- **Step 1: Build the Graph.** We first convert the given edges into an adjacency list to represent the tree.
- **Step 2: Find a Top-Down Order.** We traverse the tree starting from the root (node 0) using a simple iterative stack. Along the way, we record the order of visited nodes in an array (`temp`) and also track the `parent` of each node to know which way is "up".
- **Step 3: Process Bottom-Up.** By reversing our recorded `temp` array, we get a perfect bottom-up (post-order) sequence. This guarantees we always process children before their parents.
- **Step 4: Count and Bubble Up.** For each node in the reversed order:
  - If the node's total accumulated value modulo `k` is `0`, we found a valid component! Increment our counter.
  - Otherwise, add this node's remainder to its parent's total. 
*(Note: Because the total sum of the entire tree is guaranteed to be divisible by `k` for a valid split to exist, the root node will always evaluate to `0` and will never improperly pass remainders to a non-existent parent).*

## Complexity

- **Time complexity:** `O(N)` where `N` is the number of nodes. We traverse the tree downwards once to establish parent relationships and upwards once to calculate remainders.
- **Space complexity:** `O(N)` for the adjacency list graph, `parent` array, and the `temp` ordering array.

## Code

**Python:**
```python
from collections import defaultdict
from typing import List

class Solution:
    def maxKDivisibleComponents(self, n: int, edges: List[List[int]], values: List[int], k: int) -> int:
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        parent = [-1] * n
        parent[0] = -2  # Mark root with a special parent value
        
        temp = []
        stack = [0]

        # Step 1: Traverse from root to leaves to establish parents and processing order
        while stack:
            node = stack.pop()
            temp.append(node)

            for neighbor in graph[node]:
                if parent[neighbor] == -1:  # Unvisited neighbor
                    parent[neighbor] = node
                    stack.append(neighbor)

        # Use remainders to prevent integer overflow for large values
        total = [v % k for v in values]
        components_count = 0

        # Step 2: Process from leaves back to the root
        for node in reversed(temp):
            if total[node] % k == 0:
                # Valid component found
                components_count += 1
            else:
                # Pass remainder up to parent
                total[parent[node]] = (total[parent[node]] + total[node]) % k

        return components_count
```

**Java:**
```java
import java.util.*;

class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        parent[0] = -2;

        List<Integer> temp = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        // Step 1: Establish parents and processing order
        while (!stack.isEmpty()) {
            int node = stack.pop();
            temp.add(node);

            for (int neighbor : graph[node]) {
                if (parent[neighbor] == -1) {
                    parent[neighbor] = node;
                    stack.push(neighbor);
                }
            }
        }

        int[] total = new int[n];
        for (int i = 0; i < n; i++) {
            total[i] = values[i] % k;
        }
        
        int componentsCount = 0;

        // Step 2: Process bottom-up
        for (int i = temp.size() - 1; i >= 0; i--) {
            int node = temp.get(i);
            if (total[node] % k == 0) {
                componentsCount++;
            } else {
                total[parent[node]] = (total[parent[node]] + total[node]) % k;
            }
        }

        return componentsCount;
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
