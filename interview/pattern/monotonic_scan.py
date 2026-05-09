from typing import List


class ValidElementsInArray:
    def findValidElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        if n <= 2:
            return nums

        is_valid = [False] * n
        is_valid[0] = is_valid[n-1] = True
        curr_max = nums[0]
        for i in range(1, n-1):
            if nums[i] > curr_max:
                is_valid[i] = True
                curr_max = nums[i]
        curr_max = nums[n-1]
        for i in range(n-2, 0, -1):
            if nums[i] > curr_max:  # van phai quet de cap nhat max
                is_valid[i] = True
                curr_max = nums[i]

        return [nums[i] for i in range(n) if is_valid[i]]


sol = ValidElementsInArray()
print(sol.findValidElements([1, 2, 4, 2, 3, 2]))


class FinalPricesWithSpecialDiscountInAShop:
    def finalPrices(self, prices: List[int]) -> List[int]:
        n = len(prices)
        stack = []
        res = []

        for i in range(n-1, -1, -1):
            while stack and prices[i] < stack[-1]:
                stack.pop()

            if stack:
                res.append(prices[i] - stack[-1])
            else:
                res.append(prices[i])

            stack.append(prices[i])
        res.reverse()
        return res


sol = FinalPricesWithSpecialDiscountInAShop()
print(sol.finalPrices([8, 4, 6, 2, 3]))


class MaximumAmountOfMoneyRobot:
    def maximumAmount(self, coins: List[List[int]]) -> int:
        m, n = len(
