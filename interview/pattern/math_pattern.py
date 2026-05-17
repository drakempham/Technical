from collections import defaultdict
from typing import List
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


class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        # n= len(s)

        # for move in range(n):
        #     temp = s[move:] + s[:move]

        #     if temp == goal:
        #         return True
        # return False

        return len(goal) == len(s) and s in (goal + goal)


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letter_logs = []
        digits_logs = []

        for log in logs:
            identifier, content = log.split(" ", maxsplit=1)
            if content[0].isalpha():
                letter_logs.append((content, identifier, log))
            else:
                digits_logs.append(log)

        letter_logs.sort()

        return [log for _, _, log in letter_logs] + digits_logs


class Solution:
    def countTheNumberOfKTreeSubsets(self, nums: List[int], k: int) -> int:
        group_remainer= defaultdict(list)
        for num in nums:
            group_remainer[num%k].append(num)
        for groupIdx in group_remainer:
            group_remainer[groupIdx].sort()
        def subsets(idx: int):
            n = len(group_remainer[idx])
            dp = [0] * (n+1)
            dp[0] = 1
            dp[1] = 2

            for i in range(2, n+1):
                if group_remainer[idx][i-1] - group_remainer[idx][i-2] == k:
                    dp[i] = dp[i-1] + dp[i-2]
                else:
                    dp[i] = 2*dp[i-1]
            return dp[n]

        total_subset = 1
        for i in range(k):
            if group_remainer[i]:
                total_subset *= subsets(i)
        return total_subset

sol = Solution()
print(f"currSol + {sol.countTheNumberOfKTreeSubsets([2,3,5,8], 5)}")

from typing import List
class Solution:
    # def wiggleSort(self, nums: List[int]) -> None:
    #     sorted_nums = sorted(nums)
    #     n = len(nums)
    #     j = 0
    #     for i in range(0, n-1, 2):
    #         nums[i] = sorted_nums[j]
    #         nums[i+1] = sorted_nums[n-1-j]
    #         j += 1
    #     if n % 2 ==1:
    #         nums[n-1] = sorted_nums[n//2]
    #     return nums
    def wiggleSort(self, nums: List[int]):
        isSmaller = True
        for i in range(len(nums)-1):
            if isSmaller:
                if nums[i] > nums[i+1]:
                    nums[i] , nums[i+1] = nums[i+1], nums[i]
                isSmaller = not isSmaller
            else:
                if nums[i] <  nums[i+1]:
                    nums[i] , nums[i+1] = nums[i+1], nums[i]
                isSmaller = not isSmaller

sol = Solution()
print(sol.wiggleSort([3,5,2,1,6,4]))
print(sol.wiggleSort([1,2,3]))