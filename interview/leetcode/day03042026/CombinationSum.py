from typing import List


class Solution:
    # phai co start de khong dem lai element cu
    # sort truoc de tranh bi trung nhau va break som neu ko thoa dieu kien
    # dung[:] de copy array

    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        if not candidates:
            return []
        res = []

        candidates.sort()

        # EMPLOYESS.SORT(KEY=LAMBDA X : X)
        def backtrack(start: int, currComb: List[int], remain: int):
            if remain == 0:
                res.append(currComb[:])
                return
            if remain < 0 or remain < candidates[start]:
                return
            for i in range(start, len(candidates)):
                if candidates[i] > remain:
                    break
                currComb.append(candidates[i])
                backtrack(i, currComb, remain - candidates[i])
                currComb.pop()

        backtrack(0, [], target)
        return res


sol = Solution()
print(sol.combinationSum([2, 3, 6, 7], 7))
