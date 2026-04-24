# substring nhỏ nhất với ràng buộc
from collections import Counter


class RemoveDuplicatesLetter:
    def removeDuplicateLetters(self, s: str) -> str:
        counter = Counter()
        for ch in s:
            counter[ch] += 1
        stack = []
        seen = set()
        for ch in s:
            counter[ch] -= 1
            if ch in seen:
                continue
            while len(stack) > 0 and stack[-1] > ch and counter[stack[-1]] > 0:
                seen.remove(stack[-1])
                stack.pop()
            stack.append(ch)
            seen.add(ch)
        return "".join(stack)


sol = RemoveDuplicatesLetter()
print(sol.removeDuplicateLetters("bcabc"))
