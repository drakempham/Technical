class Solution:
    def char_to_num(self, text: str) -> int:
        return "".join(str(ord(char) - 96) for char in text)

    def num_to_char(self, text: str) -> str:
        return "".join([chr(int(num) + 96) for num in text])


sol = Solution()
print(sol.char_to_num("abc"))
print(sol.num_to_char("123"))
