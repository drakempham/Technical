from typing import List


class TrieNode:
    def __init__(self):
        self.children = [None] * 26  # [a-z]
        self.is_word = False


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, val: str):

        node = self.root
        for c in val:
            pos = ord(c) - ord('a')

            if self.children[pos] is None:
                self.children[pos] = TrieNode()

            node = self.children[pos]

        node.is_word = True

    def search(self, word: str) -> bool:
        node = self.root

        for ch in word:
            idx = ord(ch) - ord('a')

            if node.children[idx] is None:
                return False

            node = node.children[idx]

        return node.is_word

    def startsWith(self, prefix: str) -> bool:
        node = self.root

        for ch in prefix:
            idx = ord(ch) - ord('a')

            if node.children[idx] is None:
                return False

            node = node.children[idx]

        return True


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

# example
main = ReverseTrie(["cd", "f", "kl"])
print(main.query("a"))
print(main.query("b"))
print(main.query("c"))
print(main.query("d"))
print(main.query("e"))
print(main.query("f"))
print(main.query("g"))
print(main.query("h"))
print(main.query("i"))
print(main.query("j"))
print(main.query("k"))
print(main.query("l"))
print(main.query("m"))
print(main.query("n"))
print(main.query("o"))
print(main.query("p"))
print(main.query("q"))
print(main.query("r"))
print(main.query("s"))
print(main.query("t"))
print(main.query("u"))
print(main.query("v"))
print(main.query("w"))
print(main.query("x"))
print(main.query("y"))
print(main.query("z"))