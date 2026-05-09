from typing import List


class MinimumOpsToMakeUniValueGrid:
    def minOperations(self, grid: List[List[int]], x: int) -> int:
        sp_remainder = grid[0][0] % x
        temp_list = [num for row in grid for num in row]
        flat_list = sorted(temp_list)
        n = len(flat_list)
        median_num = flat_list[n // 2]
        total_ops = 0

        for num in flat_list:
            if num % x != sp_remainder:
                return -1
            total_ops += abs(num - median_num) // x

        return total_ops


sol = MinimumOpsToMakeUniValueGrid()
print(sol.minOperations([[2, 4], [6, 8]], 2))
