from typing import List


class CombinationSumIII:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
        result = []
        if n > 45 or k > n:
            return []

        def backtrack(result: List[List[int]], temp: List[int], remain: int, k: int, start: int):
            if k == len(temp):
                if remain == 0:
                    result.append(temp[:])
                return
            for i in range(start, 10):
                if i <= remain:
                    temp.append(i)
                    backtrack(result, temp, remain - i, k, i+1)
                    temp.pop()
                else:
                    break
        backtrack(result, [], n, k, 1)
        return result

    def combinationSum3Simple(self, k: int, n: int) -> List[List[int]]:
        ans = []
        temp = []

        def dfs(start: int, remain: int):
            if len(temp) == k:
                if remain == 0:
                    ans.append(temp[:])
                return
            if start > remain or start > 9:
                return
            temp.append(start)
            dfs(start+1, remain - start)
            temp.pop()
            dfs(start + 1, remain)
        dfs(1, n)
        return ans


sol = CombinationSumIII()
# print(sol.combinationSum3(3, 7))
# print(sol.combinationSum3(3, 9))
print(sol.combinationSum3Simple(9, 45))
