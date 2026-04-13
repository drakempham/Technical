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
