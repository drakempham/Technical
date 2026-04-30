# Beginner Friendly ⛳️ || Python & Java || Trie || Reverse Search

## Intuition

Imagine you are receiving a continuous stream of characters and you need to know if any suffix of the characters you've seen so far forms a word from a given dictionary. If we search forwards, we'd have to check multiple starting points every time a new character arrives. Instead, what if we read the words backwards and search backwards? By storing all words reversed in a Trie, every time a new character arrives, we just look back through our history of received characters and trace down the Trie from the root!

## Why This Works

A Trie (Prefix Tree) is excellent for finding prefixes. Since we want to find if any word is a *suffix* of the stream, we can reverse the problem! We insert the reversed versions of all our dictionary words into a Trie. Then, as characters stream in, we simply iterate backwards through the history of received characters and traverse the Trie. If we hit a node marked as `is_word`, we've found a matching suffix. If we hit a missing path, we know no word matches.

## Approach

- **Step 1:** Create a `TrieNode` structure with an array of 26 children (for a-z) and a boolean `is_word` flag.
- **Step 2:** In the `__init__` method, initialize the root node and an empty list `stream_characters` to keep track of the incoming stream.
- **Step 3:** Loop through every word in the given dictionary. For each word, iterate through its characters in **reverse order** and insert them into the Trie. Mark the final node as `is_word = True`.
- **Step 4:** For the `query(letter)` method, first append the new letter to our `stream_characters` history.
- **Step 5:** Start at the root of the Trie and iterate backwards through the `stream_characters`.
- **Step 6:** For each character, check the corresponding child node. If we find a node where `is_word` is `True`, it means we've successfully traced a complete reversed word, so return `True`. If the child node doesn't exist, return `False`. If the loop finishes, return the `is_word` status of the current node.

## Complexity

- Time complexity: 
  - Initialization: `O(W)` where `W` is the total number of characters across all words in the dictionary.
  - Query: `O(M)` where `M` is the length of the longest word in the dictionary. We only need to check backwards up to the length of the longest word before either finding a match or hitting a dead end.
- Space complexity: `O(W + N)` where `W` is the space needed to store the Trie and `N` is the number of characters we store in the `stream_characters` list.

## Code

**Python:**
```python
from typing import List

class TrieNode:
    def __init__(self):
        self.children = [None] * 26  # [a-z]
        self.is_word = False

class ReverseTrie:
    def __init__(self, words: List[str]):
        self.root = TrieNode()
        self.stream_characters = []

        for word in words:
            node = self.root
            for c in reversed(word):
                pos = ord(c) - ord('a')
                if node.children[pos] is None:
                    node.children[pos] = TrieNode()
                node = node.children[pos]
            node.is_word = True

    def query(self, letter: str) -> bool:
        self.stream_characters.append(letter)

        node = self.root
        for c in reversed(self.stream_characters):
            pos = ord(c) - ord('a')
            if node.is_word:
                return True
            if node.children[pos] is None:
                return False
            node = node.children[pos]

        return node.is_word
```

**Java:**
```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
}

class ReverseTrie {
    private TrieNode root;
    private StringBuilder streamCharacters;

    public ReverseTrie(String[] words) {
        root = new TrieNode();
        streamCharacters = new StringBuilder();

        for (String word : words) {
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int pos = word.charAt(i) - 'a';
                if (node.children[pos] == null) {
                    node.children[pos] = new TrieNode();
                }
                node = node.children[pos];
            }
            node.isWord = true;
        }
    }

    public boolean query(char letter) {
        streamCharacters.append(letter);

        TrieNode node = root;
        for (int i = streamCharacters.length() - 1; i >= 0; i--) {
            int pos = streamCharacters.charAt(i) - 'a';
            if (node.isWord) {
                return true;
            }
            if (node.children[pos] == null) {
                return false;
            }
            node = node.children[pos];
        }

        return node.isWord;
    }
}
```

## Similar problem

Some example problems with same pattern
- 1032. Stream of Characters
- 208. Implement Trie (Prefix Tree)

<!-- 💡 Thought for 1 min:
Notice that our stream_characters list keeps growing indefinitely with every query! In a real-world scenario where the stream never stops, this would eventually cause a Memory Leak (OutOfMemoryError). How could we optimize this space complexity?  -->

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
