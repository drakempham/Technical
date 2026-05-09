from typing import List


class Greedy:
    def jump(self, nums: List[int]) -> int:
        farthest = 0
        jump = 0
        curr_far = 0
        for i in range(0, len(nums)):
            farthest = max(farthest, i + nums[i])
            if farthest == len(nums)-1:
                jump += 1
                break
            if curr_far == i:
                jump += 1
                curr_far = farthest
        return jump


sol = Greedy()
# print(sol.jump([2, 3, 1, 1, 4]))
# print(sol.jump([0]))
print(sol.jump([1, 1, 1, 1]))


class RemoveKDigits:
    def removeKdigits(self, num: str, k: int) -> str:
        stack = [num[0]]
        i = 1
        max_len = len(num) - k
        while i < len(num):
            while stack and k and num[i] < stack[-1]:
                stack.pop()
                k -= 1
            stack.append(num[i])
            i += 1

        # chuoi con lai la tang dan, v nen lay toi da k ki tu dau
        return ''.join(stack[:max_len]).lstrip('0') or '0'

        # while k > 0:
        #     stack.pop()
        #     k -= 1
        # if len(stack) > 0:
        #     while len(stack) > 0 and stack[0] == '0':
        #         stack = stack[1:]
        # if len(stack) == 0:
        #     stack.append('0')
        # return ''.join(stack)


sol = RemoveKDigits()
# print(sol.removeKdigits("1432", 2))
# print(sol.removeKdigits("1234", 2))
# print(sol.removeKdigits("10200", 1))
# print(sol.removeKdigits("10", 1))
print(sol.removeKdigits("33526221184202197273", 19))


class AdditiveNumber:
    def isAdditiveNumber(self, num: str) -> bool:
        n = len(num)
        for i in range(1, n//2 + 1):
            for j in range(i+1,  n):
                first_ele = num[0:i]
                second_ele = num[i:j]
                if first_ele[0] == '0' or second_ele[0] == '0':
                    continue
                start = j
                exist = True
                while start < n:
                    first_num = int(first_ele)
                    second_num = int(second_ele)

                    total = first_num + second_num

                    if not num.startswith(str(total), start):
                        exist = False
                        break
                    start += len(str(total))
                    first_ele, second_ele = second_ele, str(total)
                if exist == True:
                    return True

        return False


sol = AdditiveNumber()
# print(sol.isAdditiveNumber("112359"))
print(sol.isAdditiveNumber("199111992"))


class IncreasingTripletSubsequence:
    def increasingTriplet(self, nums: List[int]) -> bool:
        if len(nums) < 3:
            return False
        first_num, second_num = float('inf'), float('inf')
        for ele in nums:
            if ele <= first_num:
                first_num = ele
            elif ele <= second_num:
                second_num = ele
            else:
                return True
        return False


class TwoFurthestHousesWithDifferentColors:
    def maxDistance(self, colors: List[int]) -> int:
        n = len(colors)
        ans = float('-inf')
        for i in range(n):
            if colors[i] != colors[-1]:
                ans = max(ans, n-1 - i)
            if colors[i] != colors[0]:
                ans = max(ans, i)
        return ans


sol = TwoFurthestHousesWithDifferentColors()
print(sol.maxDistance([1, 1, 1, 6, 1, 1, 1]))
