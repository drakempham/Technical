class IntegerBreak:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
        ans = 1
        while True:
            if n <= 4:
                ans *= n
                break
            else:
                ans *= 3
                n -= 3

        return ans


sol = IntegerBreak()
print(sol.integerBreak(10))


class MirrorDistanceOfInteger:
    # def mirrorDistance(self, n: int) -> int:
    #     return abs(n - int(str(n)[::-1]))

    def mirrorDistance(self, n: int) -> int:
        a = abs(n)
        rev = 0
        while a > 0:
            rev = rev*10 + a % 10
            a //= 10
        return abs(n-rev) if n > 0 else abs(n+rev)


sol = MirrorDistanceOfInteger()
print(sol.mirrorDistance(25))
print(sol.mirrorDistance(-25))


class MinimumOperationsToMakeArrayNonDescending:
    def minOperations(self, nums: list[int]) -> int:
        tax = 0

        for p, q in zip(nums, nums[1:]):
            if p > q:
                tax += p - q

        return tax