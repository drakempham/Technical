from re import S


class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        text_list = list(s)
        left, right = 0, len(s) - 1
        while left < right:
            while left < len(s) and not text_list[left].isalpha():
                left += 1
            while right > 0 and not text_list[right].isalpha():
                right -= 1

            # check neu trong vong lap left right tang giam co the break logic app
            if left >= len(s) or right <= 0 or left >= right:
                break
            text_list[left], text_list[right] = text_list[right], text_list[left]
            left += 1
            right -= 1
        return "".join(text_list)


solution = Solution()
print(solution.reverseOnlyLetters("?6C40E"))
