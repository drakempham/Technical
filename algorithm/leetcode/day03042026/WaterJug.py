
class Solution:
    # Bezout identity
    def canMeasureWater(self, x: int, y: int, target: int) -> bool:
        if (x+y) < target:
            return False

        def gcd(x, y) -> int:
            while y != 0:
                x, y = y, x % y
            return x
        z = gcd(x, y)
        return target >= z and target % z == 0


sol = Solution()
print(sol.canMeasureWater(3, 5, 4))
