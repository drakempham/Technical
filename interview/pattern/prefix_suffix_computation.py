class SmallestStableIndexI:
    def firstStableIndex(self, nums: list[int], k: int) -> int:
        n = len(nums)
        suf_arr = [float('inf')] * n
        suf_arr[n-1] = nums[n-1]
        for i in range(n-2, -1, -1):
            suf_arr[i] = min(nums[i], suf_arr[i+1])
        pref_arr = [float('-inf')] * n
        pref_arr[0] = nums[0]
        for i in range(n):
            if i > 0:
                pref_arr[i] = max(nums[i], pref_arr[i-1])
            if pref_arr[i] - suf_arr[i] <= k:
                return i
        return -1


sol = SmallestStableIndexI()
print(sol.firstStableIndex([5, 0, 1, 4], 3))
print(sol.firstStableIndex([1], 1)
      )
