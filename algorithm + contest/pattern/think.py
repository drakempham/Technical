from typing import List


class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letter_logs = []
        digits_logs = []

        for log in logs:
            identifier, content = log.split(" ", maxsplit=1)
            if content[0].isalpha():
                letter_logs.append((content, identifier, log))
            else:
                digits_logs.append(log)

        letter_logs.sort()

        return [log for _, _, log in letter_logs] + digits_logs


sol = Solution()
print(sol.reorderLogFiles(["dig1 8 1 5 1", "let1 art can",
      "dig2 3 6", "let2 own kit dig", "let3 art zero"]))
