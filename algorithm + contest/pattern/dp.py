from typing import List


class HouseRobber:
    def rob(self, nums: List[int]) -> int:
        # rob and not_rob
        n = len(nums)
        rob = [0] * n
        not_rob = [0] * n

        rob[0] = nums[0]

        for i in range(1, len(nums)):
            rob[i] = not_rob[i-1] + nums[i]
            not_rob[i] = max(rob[i-1], not_rob[i-1])

        return max(rob[-1], not_rob[-1])


class LongestPalindromeSubStr:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        if n == 0:
            return ""

        max_len = 1
        ans = s[0]

        dp = [[False for _ in range(n)] for _ in range(n)]

        for i in range(n):
            dp[i][i] = True

        for length in range(2, n + 1):
            for left in range(0, n - length + 1):
                right = left + length - 1

                if s[left] == s[right]:
                    dp[left][right] = length == 2 or dp[left + 1][right - 1]

                    if dp[left][right] and length > max_len:
                        max_len = length
                        ans = s[left:right + 1]

        return ans


sol = LongestPalindromeSubStr()
# print(sol.longestPalindrome("babad"))
# print(sol.longestPalindrome("cbbd"))
print(sol.longestPalindrome("aaaa"))


class SuperUglyNumber:
    # neu ko phai prime se duplicate rat nhieu
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        dp = [0] * n
        prime_len = len(primes)
        dp[0] = 1  # not construct by any primes -> only include in primes
        idx = [0] * prime_len
        cand = [0] * prime_len
        for i in range(1, n):
            for j in range(prime_len):
                cand[j] = dp[idx[j]] * primes[j]
            min_val = min(cand)
            dp[i] = min_val
            for j in range(prime_len):
                if cand[j] == min_val:
                    idx[j] += 1
        return dp[n-1]


sol = SuperUglyNumber()
print(sol.nthSuperUglyNumber(5, [2, 7, 13]))


class LargestDivisibleSubset:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        dp = [1] * n
        parent = [-1] * n
        ans_idx = 0
        for i in range(1, n):
            for j in range(0, i):
                if nums[i] % nums[j] == 0:
                    dp[i] = max(dp[i], dp[j] + 1)
                    if dp[i] == dp[j] + 1:
                        parent[i] = j
            # chot ans của dp[i] o buoc cuoi
            if dp[i] > dp[ans_idx]:
                ans_idx = i
        result = []
        while ans_idx != -1:
            result.append(nums[ans_idx])
            ans_idx = parent[ans_idx]
        return result[::-1]


sol = LargestDivisibleSubset()
print(sol.largestDivisibleSubset([1, 2, 3]))
