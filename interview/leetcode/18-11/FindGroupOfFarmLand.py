import re
from typing import List


class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        row2, col2 = 0, 0
        result = []
        for i in range(len(land)):
            for j in range(len(land[0])):
                if land[i][j] == 1:  # i, j is the row1, and col1 - topleft of the rectangle
                    row2 = i
                    col2 = j

                    while row2 + 1 < len(land) and land[row2+1][j] == 1:
                        row2 += 1
                    while col2 + 1 < len(land[0]) and land[i][col2 + 1] == 1:
                        col2 += 1

                    for x in range(i, row2+1):
                        for y in range(j, col2 + 1):
                            land[x][y] = 0
                    result.append([i, j, row2, col2])
        return result


solution = Solution()
# [[0, 0, 0, 0], [1, 1, 2, 2]]
result = solution.findFarmland([[1, 0, 0], [0, 1, 1], [0, 1, 1]])
print(result)
