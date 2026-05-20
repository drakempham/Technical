from collections import defaultdict
from typing import List


class LengthOfLongestSubString:
    def build(self, s: str) -> int:
        words = set()
        max_len = 0
        left, right = 0, 0

        for right in range(len(s)):
            while s[right] in words:
                words.remove(s[left])
                left += 1
            words.add(s[right])
            max_len = max(max_len, right - left + 1)
        return max_len


sol = LengthOfLongestSubString()
print(sol.build("abcbc"))


class MoveZeros:
    def moveZeroes(self, nums: List[int]) -> None:
        left = 0
        for right in range(len(nums)):
            if nums[right] != 0 and left != right:
                nums[left], nums[right] = nums[right], nums[left]
            left += 1


sol = MoveZeros()
print(sol.moveZeroes([0, 1, 0, 3, 12]))


class MaximumDistanceBetweenAPairOfValues:
    def maxDistance(self, nums1: List[int], nums2: List[int]) -> int:
        left, right = 0, 0
        ans = 0
        while left < len(nums1) and right < len(nums2):
            if nums1[left] <= nums2[right]:
                if left <= right:
                    ans = max(ans, right - left)
                right += 1
            else:
                left += 1
        return ans


sol = MaximumDistanceBetweenAPairOfValues()
print(sol.maxDistance([55, 30, 5, 4, 2], [100, 20, 10, 10, 5]))
print(sol.maxDistance([100, 99, 2], [50, 49, 48]))


class Solution:
    def maximumSubarraySum(self, nums: List[int], k: int) -> int:
        left = 0
        freq_counter = defaultdict(int)
        n = len(nums)
        curr_sum = 0
        ans = 0

        for right in range(n):
            curr_num = nums[right]

            freq_counter[curr_num] += 1
            curr_sum += curr_num
            
            if right - left + 1 > k:
                left_num = nums[left]

                freq_counter[left_num] -= 1
                if freq_counter[left_num] == 0:
                    del freq_counter[left_num]
                curr_sum -= left_num
                left += 1
        
            if right - left + 1 == k and len(freq_counter) == k:
                ans = max (ans, curr_sum)

        return ans

sol = Solution()
print(sol.maximumSubarraySum([1,5,4,2,9,9,9], 3)) # 4 
print(sol.maximumSubarraySum([4,4,4], 3))

from typing import List
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        two_idx = len(nums) -1
        zero_idx = 0
        i = 0
        while i <= two_idx:
            if nums[i] == 0:
                nums[i], nums[zero_idx] = nums[zero_idx], nums[i]
                zero_idx += 1
            elif nums[i] == 2:
                nums[i], nums[two_idx] = nums[two_idx], nums[i]
                two_idx -= 1
                i -= 1 # need to check nums at two idx
            i += 1
sol = Solution()
# nums = [1,0,2,0,0]
nums = [2,0,1]
print(sol.sortColors(nums))
print(nums)
        
        