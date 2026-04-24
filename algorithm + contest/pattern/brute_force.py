from collections import defaultdict
from typing import List


class WordWithinTwoEdit:
    # brute force - check each word in queries with each word in dictionary
    # if the difference is less than or equal to 2, add to the result
    # time complexity: O(n * m * k) -> if length queries and dirctinaries large should not use
    # space complexity: O(1)
    def twoEditWords(self, queries: List[str], dictionary: List[str]) -> List[str]:
        ans = []
        for query in queries:
            for word in dictionary:
                count = 0
                for i, c in enumerate(query):
                    if c != word[i]:
                        count += 1
                    if count > 2:
                        break

                if count <= 2:
                    ans.append(query)
                    break

        return ans

    #  generate pattern
    def twoEditWordsWithPattern(self, queries: List[str], dictionary: List[str]) -> List[str]:
        ans = 0

        def generatePattern(word: str) -> List[str]:
            pattern = [word]
            n = len(word)

            for i in range(n):
                pattern.append(word[:i] + '*' + word[i+1:])

            for i in range(n-1):
                for j in range(i+1, n):
                    s = list(word)
                    s[i], s[j] = '*', '*'
                    pattern.append(''.join(s))
            return pattern

        patterns = set()
        for word in dictionary:
            patterns.update(generatePattern(word))

        for query in queries:
            query_patterns = generatePattern(query)
            for pattern in query_patterns:
                if pattern in patterns:
                    ans += 1
                    break
        return ans


sol = WordWithinTwoEdit()
print(sol.twoEditWordsWithPattern(
    ["word", "note", "ants", "wood"], ["wood", "joke", "moat"]))


class Solution:
    def distance(self, nums: List[int]) -> List[int]:
        pos = defaultdict(list)
        for i, num in enumerate(nums):
            pos[num].append(i)
        ans = [0] * len(nums)
        for i, num in enumerate(nums):
            sum = 0
            for ele in pos[num]:
                sum += abs(i - ele)
            ans[i] = sum
        return ans
        