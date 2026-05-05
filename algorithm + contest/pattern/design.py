# Below is the interface for Iterator, which is already defined for you.
#
# class Iterator:
#     def __init__(self, nums):
#         """
#         Initializes an iterator object to the beginning of a list.
#         :type nums: List[int]
#         """
#
#     def hasNext(self):
#         """
#         Returns true if the iteration has more elements.
#         :rtype: bool
#         """
#
#     def next(self):
#         """
#         Returns the next element in the iteration.
#         :rtype: int
#         """

from numpy import random
from random import randint
from typing import List
from collections import defaultdict


class PeekingIterator:
    # def __init__(self, iterator):
    #     """
    #     Initialize your data structure here.
    #     :type iterator: Iterator
    #     """
    #     self.iterator = iterator
    #     self.next_val = self.iterator.next() if self.iterator.hasNext() else None

    # def peek(self):
    #     return self.next_val

    # def next(self):
    #     val = self.next_val
    #     self.next_val = self.iterator.next() if self.iterator.hasNext() else None
    #     return val

    # def hasNext(self):
    #     return self.next_val is not None
    def __init__(self, iterator):
        self.iterator = iterator
        self.next_val = None
        self.is_peek = False

    def peek(self):
        if not self.is_peek:
            self.is_peek = True
            self.next_val = self.iterator.next()
        return self.next_val

    def next(self):
        if self.is_peek:
            self.is_peek = False
            return self.next_val
        return self.iterator.next()

    def hasNext(self):
        return self.is_peek or self.iterator.hasNext()


# Your PeekingIterator object will be instantiated and called as such:
# iter = PeekingIterator(Iterator(nums))
# while iter.hasNext():
#     val = iter.peek()   # Get the next element but not advance the iterator.
#     iter.next()         # Should return the same value as [val].


# Description
# Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

# Implement the WordDistance class:

# WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
# int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.


# Example 1:

# Input
# ["WordDistance", "shortest", "shortest"]
# [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
# Output
# [null, 3, 1]

# Explanation
# WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
# wordDistance.shortest("coding", "practice"); // return 3
# wordDistance.shortest("makes", "coding");    // return 1
# 13:51
class WordDistance:

    # Hàm khởi tạo (Constructor)
    def __init__(self, wordsDict: List[str]):
        # default_factory: a callable ( like int) that provide the values for missing key
        self.pos = defaultdict(list)
        for i, word in enumerate(wordsDict):
            self.pos[word].append(i)
    # Hàm thực hiện tìm khoảng cách

    def shortest(self, word1: str, word2: str) -> int:
        i, j = 0, 0
        m, n = len(self.pos[word1]), len(self.pos[word2])
        ans = float('inf')
        while i < m and j < n:
            distance = self.pos[word1][i] - self.pos[word2][j]
            ans = min(ans, abs(distance))
            if distance < 0:
                i += 1
            else:
                j += 1
        return ans


wordsDict = ["practice", "makes", "perfect", "coding", "makes"]
sol = WordDistance(wordsDict)
print(sol.shortest("coding", "practice"))
print(sol.shortest("makes", "coding"))

# 14:29


class ShuffleAnArray:
    def __init__(self, nums: List[int]):
        self.origin = nums[:]

    def reset(self) -> List[int]:
        return self.origin[:]

    def shuffle(self) -> List[int]:
        n = len(self.origin)
        copy_arr = self.origin[:]
        for i in range(0, n-1):
            j = randint(i, n-1)
            copy_arr[i], copy_arr[j] = copy_arr[j], copy_arr[i]
        return copy_arr


arr = [1, 2, 3]
sol = ShuffleAnArray(arr)
print(sol.reset())
print(sol.shuffle())
print(sol.reset())
res = sol.reset()
res[0] = 9999
print(sol.origin)


class RandomizedSet:

    def __init__(self):
        self.items = {}
        self.arr = []

    def insert(self, val: int) -> bool:
        if val not in self.items:
            self.arr.append(val)
            self.items[val] = len(self.arr) - 1
            return True
        return False

    def remove(self, val: int) -> bool:
        if val not in self.items:
            return False
        pos = self.items[val]
        last = self.arr[-1]

        self.arr[pos] = last
        self.items[last] = pos
        self.arr.pop()
        del self.items[val]
        return True

    def getRandom(self) -> int:
        return random.choice(self.arr)


sol = RandomizedSet()
print(sol.insert(1))
print(sol.insert(3))
print(sol.insert(3))
print(sol.remove(1))
print(sol.getRandom())

class Solution:
    def fib(self, n: int) -> int:
        a, b = 0, 1
        for i in range(n):
            a, b = b, a + b
        return a    

sol = Solution()
print(sol.fib(3))