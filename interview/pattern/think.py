class Solution:
    def countKthRoots(self, l: int, r: int, k: int) -> int:
        def bound_left(): # ceil(roots_k(l))
            left, right = 0,int(l**(1/k)) + 1 if l > 0 else 0
            while left < right:
                mid = left + (right-left) // 2
                perf = mid**k
                if perf == l:
                    return mid
                if perf < l:
                    left = mid + 1
                else:
                    right = mid
            return left
        def bound_right(): # floor(roos_k(r))
            left, right = 0, int(r**(1/k)) + 1 if r > 0 else 0
            while left <= right:
                mid = left + (right-left) // 2
                perf = mid**k
                if perf == r:
                    return mid
                if perf > r:
                    right = mid - 1
                else:
                    left = mid + 1
            return left - 1
        return bound_right() - bound_left() + 1

sol = Solution()
print(sol.countKthRoots(1,9,3))
print(sol.countKthRoots(8,30,2))
print(sol.countKthRoots(19,22,1))
print(sol.countKthRoots(2,3,2))