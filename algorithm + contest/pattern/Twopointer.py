class LengthOfLongestSubString:
    def build(self, s: str) -> int:
        words = set()
        max_len = 0
        left, right = 0, 0

        for right in range(len(s)):
            while s[right] in words:
                words.remove(s[left])
                left += 1
            words.add(s[right])
            max_len = max(max_len, right - left + 1)
        return max_len


sol = LengthOfLongestSubString()
print(sol.build("abcbc"))
