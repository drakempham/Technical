from math import inf


class Solution:
    def numSquares(n):

        dp = [float('inf')] * (n+1)
        # range(start, stop exclusive)
        squares: list[int] = [x**2 for x in range(1, int(n**0.5) + 1)]
        dp[0] = 0

        for i in range(1, n+1):
            for square in squares:
                if i < square:
                    break
                dp[i] = min(dp[i], dp[i-square] + 1)

        return dp[n]

    # dfs calculate from top to bottom with recursive

    def numSquares2(self, n) -> int:
        squareDict = {0: 0}

        return self.recursive(squareDict, n)

    def recursive(self, squareDict: dict, n: int) -> int:
        if n in squareDict:
            return squareDict[n]

        res = float('inf')
        i = 1
        while i*i <= n:
            res = min(res, 1 + self.recursive(squareDict, n - i*i))
            i += 1

        squareDict[n] = res
        return res


sol = Solution()

# test_inputs = [12, 13, 16, 7]
test_inputs = [12, 13, 6, 7]

for val in test_inputs:
    result = sol.numSquares2(val)
    print(f"Input: {val} | Result: {result}")
