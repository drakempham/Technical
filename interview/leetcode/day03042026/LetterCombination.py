from typing import List


class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        phone = {
            '2': 'abc',
            '3': 'def',
            '4': 'ghi',
            '5': 'jkl',
            '6': 'mno',
            '7': 'pqrs',
            '8': 'tuv',
            '9': 'wxyz'
        }
        res = list()

        def backtrack(digits: str, digitIdx: int, phone: dict, currComb: str):
            if len(currComb) == len(digits):
                res.append(currComb)
                return

            currLetter = digits[digitIdx]

            for c in phone[currLetter]:
                backtrack(digits, digitIdx+1, phone,
                          currComb + c)

        backtrack(digits, 0, phone, "")
        return res


sol = Solution()
print(sol.letterCombinations("23"))
