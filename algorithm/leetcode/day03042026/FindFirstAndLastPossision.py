from typing import List


class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        def findLeftFence(nums, target) -> int:
            left, right = 0, len(nums) - 1
            while left <= right:
                mid = left + (right-left) // 2
                if nums[mid] == target:
                    if mid == 0 or nums[mid] != nums[mid-1]:
                        return mid
                    else:
                        right = mid - 1
                elif nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid - 1

            return -1

        def findRightFence(nums, target) -> int:
            left, right = 0, len(nums) - 1
            while left <= right:
                mid = left + (right-left) // 2
                if nums[mid] == target:
                    if mid == len(nums) - 1 or nums[mid] != nums[mid+1]:
                        return mid
                    else:
                        left = mid + 1
                elif nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid - 1

            return -1

        left = findLeftFence(nums, target)
        right = findRightFence(nums, target)
        return [left, right]

    def searchRange2(self, nums: List[int], target: int) -> List[int]:
        def searchFence(isLeft) -> int:
            left, right = 0, len(nums) - 1
            res = -1
            while left <= right:
                mid = left + (right-left) // 2
                if nums[mid] == target:
                    res = mid
                    if isLeft:
                        right = mid - 1
                    else:
                        left = mid + 1

                elif nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid - 1

            return res

        return [searchFence(True), searchFence(False)]


sol = Solution()
print(sol.searchRange([5, 7, 7, 8, 8, 10], 8))
