from typing import List


class FenwickTree:
    def __init__(self, n):
        self.ft = [0] * (n + 1)
        self.n = n

    def build_from_list(self, arr):
        n = len(arr)
        ft = self.__class__(n)
        for i in range(1, n+1):
            ft.ft[i] += arr[i-1]
            parent = i + (i & (-i))
            if parent <= n:
                ft.ft[parent] += ft.ft[i]
        return ft

    def update(self, i, num):
        self.ft[i] += num
        while i <= self.n:
            i += i & (-i)
            if i > self.n:
                break
            self.ft[i] += num
        return self.ft[i]

    def query(self, i):
        sum = 0
        while i > 0:
            sum += self.ft[i]
            i -= i & (-i)
        return sum

    def query_range(self, l, r):
        return self.query(r) - self.query(l-1)


# init array
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
sol = FenwickTree(len(arr))
ft = sol.build_from_list(arr)
print(ft.query(5))
print(ft.query_range(2, 5))


class RansumQueryMutable:
    def __init__(self, nums: List[int]):
        n = len(nums)
        self.n = n
        self.nums = nums[:]
        self.ft = [0] * (n + 1)

        def build_from_list():
            for i in range(1, n + 1):
                curr_pos = i
                self.ft[curr_pos] += nums[curr_pos-1]
                new_pos = curr_pos + (curr_pos & -curr_pos)
                if new_pos <= n:
                    self.ft[new_pos] += self.ft[curr_pos]
        build_from_list()

    def update(self, index: int, val: int) -> None:
        curr_pos = index + 1
        old_val = self.nums[index]
        self.nums[index] = val
        delta = val - old_val
        while curr_pos < self.n:
            self.ft[curr_pos] += delta
            curr_pos += (curr_pos & -curr_pos)

    def sumRange(self, left: int, right: int) -> int:
        def query(pos: int):
            total = 0
            while pos >= 1:
                total += self.ft[pos]
                pos -= (pos & -pos)
            return total
        return query(right+1) - query(left)


# sol = RansumQueryMutable([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
# print(sol.sumRange(0, 4))
# sol.update(0, 10)
# print(sol.sumRange(0, 4))


sol = RansumQueryMutable([1, 3, 5])
print(sol.sumRange(0, 2))
sol.update(1, 2)
print(sol.sumRange(0, 2))
