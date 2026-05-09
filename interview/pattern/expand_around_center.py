class LongestPalindromeSubStr:
    def longestPalindrome(self, s: str) -> str:
        if not s:
            return ""
        if len(s) == 1:
            return s[0]
        if len(s) == 2:
            if s[0] == s[1]:
                return s
            return s[0]
        ans = s[0]
        for i in range(len(s)):
            left_ptr = i-1
            right_ptr = i+1
            while left_ptr >= 0 and right_ptr < len(s) and s[left_ptr] == s[right_ptr]:
                if len(ans) < right_ptr-left_ptr + 1:
                    ans = s[left_ptr:right_ptr+1]
                left_ptr -= 1
                right_ptr += 1
            if i < len(s) - 1 and s[i] == s[i+1]:
                if len(ans) < 2:
                    ans = s[i:i+2]
                left_ptr = i-1
                right_ptr = i+2
                while left_ptr >= 0 and right_ptr < len(s) and s[left_ptr] == s[right_ptr]:
                    if len(ans) < right_ptr-left_ptr + 1:
                        ans = s[left_ptr:right_ptr+1]
                    left_ptr -= 1
                    right_ptr += 1

        return ans
