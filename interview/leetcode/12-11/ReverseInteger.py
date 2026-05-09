class Solution:
    # reverse x thi cu chia 10 lien tuc, reverse thi nguoc lai *10 + remainder
    def reverse(self, x: int) -> int:
        INT_MAX = pow(2, 31)
        sign = 1 if x > 0 else -1
        x = abs(x)
        reverse = 0
        while x > 0:
            remainder = x % 10
            if reverse > (INT_MAX - remainder) // 10:
                return 0
            reverse = reverse * 10 + remainder
            x = x // 10
        return reverse * sign


# test
sol = Solution()
print(sol.reverse(-123))
