class Solution:
    def longestValidParentheses(self, s: str) -> int:
        # longest for a parenthesis not substring
        # stack = []
        # max_len = 0
        # for i in range(len(s)):
        #     if s[i] == '(':
        #         stack.append(i)
        #     else:
        #         if len(stack) > 0:
        #             last_ele = stack.pop()
        #             max_len = max(max_len, i - last_ele + 1)
        # return max_len

        # y tuong: muon tinh tat cac cac substring hop le, ta phai lay moc tu phan tu khong hop le (barrier)
        max_len = 0
        # vi tri bat dau de tinh moc (co the coi la ko hop le dau tien)
        stack = [-1]

        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                stack.pop()
                if len(stack) == 0:
                    stack.push(i)
                max_len = max(max_len, i - stack[-1])

        return max_len


solution = Solution()
print(solution.longestValidParentheses("(()())"))
print(solution.longestValidParentheses('()()'))
