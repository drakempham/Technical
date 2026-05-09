from typing import List


# class MissingNumber:
#     def missingNumber(self, nums: List[int]) -> int:
#         ans = 0
#         for i, num in enumerate(nums):
#             ans = ans ^ num ^ (i+1)
#         return ans

# sol = MissingNumber()
# print(sol.missingNumber([3, 0, 1]))

class XorQueries:
    # xor range (i,j) = xor_sum[j+1] ^ xor_sum[i] mean i-1 -> j
    def xorQueries(self, arr: List[int], queries: List[List[int]]) -> List[int]:
        xor_arr = [0] * (len(arr) + 1)
        xor_arr[0] = 0
        for i, ele in enumerate(arr):
            xor_arr[i+1] = xor_arr[i] ^ ele

        ans = [0] * len(queries)
        count = 0
        for left, right in queries:
            ans[count] = xor_arr[left] ^ xor_arr[right+1]
            count += 1
        return ans


sol = XorQueries()
print(sol.xorQueries([1, 3, 4, 8], [[0, 1], [1, 2], [0, 3], [3, 3]]))
