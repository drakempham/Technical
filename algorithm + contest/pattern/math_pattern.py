class IntegerBreak:
    def integerBreak(self, n: int) -> int:
        if n == 2:
            return 1
        if n == 3:
            return 2
        ans = 1
        while True:
            if n <= 4:
                ans *= n
                break
            else:
                ans *= 3
                n -= 3

        return ans


sol = IntegerBreak()
print(sol.integerBreak(10))


class MirrorDistanceOfInteger:
    # def mirrorDistance(self, n: int) -> int:
    #     return abs(n - int(str(n)[::-1]))

    def mirrorDistance(self, n: int) -> int:
        a = abs(n)
        rev = 0
        while a > 0:
            rev = rev*10 + a % 10
            a //= 10
        return abs(n-rev) if n > 0 else abs(n+rev)


sol = MirrorDistanceOfInteger()
print(sol.mirrorDistance(25))
print(sol.mirrorDistance(-25))


class MinimumOperationsToMakeArrayNonDescending:
    def minOperations(self, nums: list[int]) -> int:
        tax = 0

        for p, q in zip(nums, nums[1:]):
            if p > q:
                tax += p - q

        return tax


class MaxKeeper:
    def __init__(self, n: int = 0):
        self.n = n
        self.tree = [float("-inf")] * (n + 1)

    def put(self, pos: int, val: int) -> None:
        while pos <= self.n:
            if val > self.tree[pos]:
                self.tree[pos] = val
            pos += pos & -pos

    def take(self, pos: int) -> int:
        res = float("-inf")

        while pos > 0:
            if self.tree[pos] >= res:
                res = self.tree[pos]
            pos -= pos & -pos
        return res

class Solution:
    def maxAlternatingSum(self, nums: list[int], k: int) -> int:
        ordered = sorted(set(nums))
        order_rnak = {v: i + 1 for i, v in enumerate(ordered)}
        width = len(ordered)

        low = MaxKeeper(width)
        high = MaxKeeper(width)

        up = [float("-inf")] * len(nums)
        down = [float("-inf")] * len(nums)

        t = max(nums)

        for num in range(len(nums)):
            ready = num - k

            if ready >= 0:
                val = nums[ready]
                pos = order_rnak[val]

                low.put(pos, max(val, down[ready]))
                high.put(width - pos + 1, max(val, up[ready]))

            cur_val = nums[num]
            cur_pos = order_rnak[cur_val]

            prev_l = low.take(cur_pos - 1)
            if prev_l != float("-inf"):
                up[num] = prev_l + cur_val

            prev_h = high.take(width - cur_pos)
            if prev_h != float("-inf"):
                down[num] = prev_h + cur_val

            t = max(t, up[num], down[num])

        return t