from typing import List
class Solution:
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        seen = 0
        ans = []
        count = 0
        for i in range(len(A)):
            mask_A =  1 << (A[i] - 1)
            if mask_A & seen != 0:
                count += 1
            seen = seen | mask_A
            
            mask_B = 1 <<(B[i] - 1)
            if mask_B & seen != 0:
                count += 1
            seen = seen | mask_B
            ans.append(count)
        return ans


sol = Solution()
print(sol.findThePrefixCommonArray([1,3,2,4],[3,1,2,4]))