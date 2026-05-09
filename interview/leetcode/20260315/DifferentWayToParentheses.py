from typing import List


class Solution:
    def diffWaysToCompute(self, expression: str) -> List[int]:
        if expression.isdigit():
            return [int(expression)]

        computed_res = []
        for i, ele in enumerate(expression):
            if ele in '+-*':
                left_computed = self.diffWaysToCompute(expression[:i])
                right_computed = self.diffWaysToCompute(expression[i+1:])

                for left in left_computed:
                    for right in right_computed:
                        if ele == '+':
                            computed_res.append(left + right)
                        elif ele == '-':
                            computed_res.append(left-right)
                        else:
                            computed_res.append(left*right)

        return computed_res


sol = Solution()
# print(sol.diffWaysToCompute("2-1-1"))
print(sol.diffWaysToCompute("2*3-4*5"))
