from typing import List

# when you need to find the max/min on the left/right side of each index, you can use maxLeft and minRight arrays to store the values


class Solution:
    def SumOfBeautyInTheArray(self, nums: List[int]) -> int:
        n = len(nums)
        # store the largest element in the left of each i
        maxLeft = [0] * n
        minRight = [float('inf')] * n
        for i in range(1, n-1):
            maxLeft[i] = max(maxLeft[i-1], nums[i-1])
        for i in range(n-2, 0, -1):
            minRight[i] = min(minRight[i+1], nums[i+1])

        result = 0
        for i in range(1, n-1):
            if nums[i] > maxLeft[i] and nums[i] < minRight[i]:
                result += 2
            elif nums[i] > nums[i-1] and nums[i] < nums[i+1]:
                result += 1

        return result


solution = Solution()
# print(solution.SumOfBeautyInTheArray([1, 2, 3]))  # 2
print(solution.SumOfBeautyInTheArray([2, 4, 6, 4]))  # 1
