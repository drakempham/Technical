class ValidElementsInArray:
    def findValidElements(self, nums: List[int]) -> List[int]:
        n = len(nums)
        if n <= 2:
            return nums

        is_valid = [False] * n
        is_valid[0] = is_valid[n-1] = True
        curr_max = nums[0]
        for i in range(1, n-1):
            if nums[i] > curr_max:
                is_valid[i] = True
                curr_max = nums[i]
        curr_max = nums[n-1]
        for i in range(n-2, 0, -1):
            if nums[i] > curr_max:  # van phai quet de cap nhat max
                is_valid[i] = True
                curr_max = nums[i]

        return [nums[i] for i in range(n) if is_valid[i]]


sol = ValidElementsInArray()
print(sol.findValidElements([1, 2, 4, 2, 3, 2]))
