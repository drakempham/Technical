class Solution:
    # bai nay thi lan luot di len ( goingDown = false) va di xuong 0<=jump<=num(len)
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1 or numRows >= len(s):
            return s

        rows = [''] * numRows
        currRow = 0
        goingDown = True

        for c in s:
            rows[currRow] += c
            if currRow == 0:
                goingDown = True
            elif currRow == numRows - 1:
                goingDown = False
            currRow += 1 if goingDown else -1

        return ''.join(rows)


# Example usage:
sol = Solution()
print(sol.convert("PAYPALISHIRING", 3))
