class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        if len(s) <= 1:
            return True
        n = len(s)
        prev = ord(s[0])
        for i in range(1,n):
            curr = ord(s[i])
            if abs(curr-prev) > 2:
                return False
            prev = curr
        return True

sol = Solution()
print(sol.isAdjacentDiffAtMostTwo("132"))