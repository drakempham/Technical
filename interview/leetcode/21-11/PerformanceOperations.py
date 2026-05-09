from numbers import Integral
from typing import List


class Solution:
    # def finalValueAfterOperations(self, operations: List[str]) -> int:
    #     operation_list: dict[str, Integral] = {
    #         "++X": 1,
    #         "X++": 1,
    #         "--X": -1,
    #         "X--": -1
    #     }

    #     result = 0
    #     for opr in operations:
    #         result += operation_list[opr]
    #     return result

    def finalValueAfterOperations(self, operations: List[str]) -> int:
        return sum(1 if '+' in opr else -1 for opr in operations)


solution = Solution()
print(solution.finalValueAfterOperations(["--X", "X++", "X++"]))
