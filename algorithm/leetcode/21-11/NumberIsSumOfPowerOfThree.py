class Solution:
    def checkPowersOfThree(self, n: int) -> bool:
        while n > 0:
            if n % 3 == 2:
                return False
            n = n // 3
        return True


solution = Solution()
print(solution.checkPowersOfThree(12))  # True
print(solution.checkPowersOfThree(91))  # True
print(solution.checkPowersOfThree(21))  # False
