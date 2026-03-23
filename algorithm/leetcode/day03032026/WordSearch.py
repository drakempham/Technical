from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if not board or not word:
            return False
        visited = set()

        def dfs(i: int, j: int, idx: int) -> bool:
            if idx == len(word):
                return True
            if i < 0 or i >= len(board) or j < 0 or j >= len(board[0]) or (i*len(board[0]) + j) in visited or board[i][j] != word[idx]:
                return False

            visited.add(i*len(board[0]) + j)

            found = dfs(i-1, j, idx+1) or dfs(i, j+1, idx +
                                              1) or dfs(i+1, j, idx+1) or dfs(i, j-1, idx+1)

            visited.remove(i*len(board[0]) + j)

            return found

        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] == word[0]:
                    found = dfs(i, j, 0)

                    if found:
                        return True
        return False


sol = Solution()

print(sol.exist([["A", "B", "C", "E"], [
      "S", "F", "C", "S"], ["A", "D", "E", "E"]], "SEE"))
