from collections import defaultdict


class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        char_count = defaultdict(int)
        left, right = 0, 0
        n = len(s)
        ans = 0
        while right < n:
            char_count[s[right]] += 1
            while len(char_count) > 2:
                char_count[s[left]] -= 1
                left += 1
            ans = min(ans, right - left + 1)
            right += 1
        return ans
