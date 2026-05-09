from typing import List


class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        hold = -prices[0]
        sold = 0
        rest = 0

        for i in range(1, len(prices)):
            prev_hold = hold
            prev_sold = sold
            prev_rest = rest

            hold = max(prev_hold, prev_rest - prices[i])
            sold = prev_hold + prices[i]
            rest = max(prev_sold, prev_rest)

        return max(sold, rest)


sol = Solution()
print(sol.maxProfit([1, 2, 3, 0, 2]))
