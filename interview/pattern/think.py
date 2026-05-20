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
        
        