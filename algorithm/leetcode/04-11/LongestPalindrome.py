class Solution:
    def longestPalindrome(self, s: str) -> str:
        res = ""
        for i in range(1, len(s)):
            # odd length
            tmp = self.expand(s, i, i)
            if len(tmp) > len(res):
                res = tmp

            # event length
            tmp = self.expand(s, i, i+1)
            if len(tmp) > len(res):
                res = tmp

        return res

    def expand(self, s: str, left: int, right: int) -> str:
        # don't count last index, this cause it increase 1
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left+1:right]  # left + 1 -> right  - 1

    # TODO: dynamic programming


solution = Solution()
print(solution.longestPalindrome("babad"))
