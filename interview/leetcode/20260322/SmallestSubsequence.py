class Solution:
    def smallestSubsequence(self, s: str) -> str:
        if len(s) <= 1:
            return s
        s = ''.join(sorted(s))
        res = s[0]
        for i in range(1, len(s)):
            if s[i] != s[i-1]:
                res += s[i]
        return res

    def smallestSubsequence(self, s: str) -> str:


sol = Solution()
print(sol.smallestSubsequence("cbacdcbc"))
