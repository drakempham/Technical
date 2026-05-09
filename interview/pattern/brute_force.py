

from functools import lru_cache
from typing import List


class TrappingWater:
    # brute force
    # def trap(self, height: List[int]):
    #     ans = 0
    #     for i in range(1, len(height)):
    #         max_left, max_right = 0, 0
    #         for j in range(i,-1,-1):
    #             max_left = max(max_left, height[j])
    #         for j in range(i, len(height), 1):
    #             max_right = max(max_right, height[j])
    #         ans += min(max_left, max_right) - height[i]
    #     return ans
    # prefix:
    # def trap(self, height: List[int]) -> int:
    #     # Case of empty height list
    #     if len(height) == 0:
    #         return 0
    #     ans = 0
    #     size = len(height)
    #     # Create left and right max arrays
    #     left_max, right_max = [0] * size, [0] * size
    #     # Initialize first height into left max
    #     left_max[0] = height[0]
    #     for i in range(1, size):
    #         # update left max with current max
    #         left_max[i] = max(height[i], left_max[i - 1])
    #     # Initialize last height into right max
    #     right_max[size - 1] = height[size - 1]
    #     for i in range(size - 2, -1, -1):
    #         # update right max with current max
    #         right_max[i] = max(height[i], right_max[i + 1])
    #     # Calculate the trapped water
    #     for i in range(1, size - 1):
    #         ans += min(left_max[i], right_max[i]) - height[i]
    #     # Return amount of trapped water
    #     return ans
    def trap(self, height: List[int]):
        ans = 0
        stack = []
        curr = 0
        while curr < len(height):
            while len(stack) > 0 and height[curr] > height[stack[-1]]:
                bottom_height = height[stack.pop()]
                if len(stack) == 0:
                    break
                curr_width = curr - stack[-1] - 1
                curr_height = min(
                    height[curr], height[stack[-1]]) - bottom_height
                ans += curr_width * curr_height
            stack.append(curr)
            curr += 1
        return ans

    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        left, right = 0, len(height) - 1
        ans = 0
        left_max, right_max = 0, 0
        while left < right:
            if height[left] < height[right]:
                left_max = max(left_max, height[left])
                ans += left_max - height[left]
                left += 1
            else:
                right_max = max(right_max, height[right])
                ans += right_max - height[right]
                right -= 1
        return ans


sol = TrappingWater()
print(sol.trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]))


class RotatedDigits:
    # get a diff number from x
    # each digit must be rotated
    # def rotatedDigits(self, n: int) -> int:
    #     valid_nums = set('0182569')
    #     to_be_rotate_nums = set('2569')
    #     count = 0
    #     for i in range(1, n+1):
    #         all_valid = True
    #         to_be_rotate = False
    #         for c in str(i):
    #             if c not in valid_nums:
    #                 all_valid = False
    #                 break
    #             if c in to_be_rotate_nums:
    #                 to_be_rotate = True
    #         if all_valid and to_be_rotate:
    #             count += 1
    #     return count

    def rotatedDigits(self, n: int) -> int:
        arr = list(map(int, str(n)))
        valid_nums = {0, 1, 8, 2, 5, 6, 9}
        rotated_nums = {2, 5, 6, 9}
        n = len(arr)

        # dp dung de tinh ket qua

        @lru_cache(maxsize=128)
        def dp(currIdx: int, is_tight: bool, has_rotated_digit: bool):
            ans = 0
            if currIdx == n:
                return 1 if has_rotated_digit else 0
            next_limit = arr[currIdx] if is_tight else 9
            for i in range(next_limit+1):
                if i not in valid_nums:
                    continue
                next_is_tight = is_tight and i == arr[currIdx]
                x = 0
                next_has_rotated_digit = has_rotated_digit or i in rotated_nums
                if dp(currIdx + 1, next_is_tight, next_has_rotated_digit) > 0:
                    x = dp(currIdx + 1, next_is_tight, next_has_rotated_digit)
                ans += x
            return ans
        return dp(0, True, False)


sol = RotatedDigits()
# print(sol.rotatedDigits(10))
print(sol.rotatedDigits(20))
