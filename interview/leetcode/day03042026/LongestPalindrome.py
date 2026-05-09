class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        if n <= 1:
            return s
        dp = [[False] * n for _ in range(n)]

        for i in range(n):
            dp[i][i] = True

        res = s[0]
        for i in range(2, n + 1):
            for j in range(0, n - i + 1):
                if s[j] == s[j+i-1]:
                    if i == 2:
                        dp[j][j+i-1] = True
                    else:
                        dp[j][j+i-1] = dp[j+1][j+i-2]

                if dp[j][j+i-1] and i > len(res):
                    res = s[j:j+i]
        return res


sol = Solution()
# print(sol.longestPalindrome("babad"))
print(sol.longestPalindrome("bb"))
