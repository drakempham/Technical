from typing import List


class HIndexII:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        left = 0
        right = len(citations) - 1
        while left <= right:
            mid = left + (right-left) // 2
            if citations[mid] >= n-mid:  # n-mid is h-index
                right = mid - 1
            else:
                left = mid + 1
        return n - left


sol = HIndexII()
print(sol.hIndex([0, 1, 3, 5, 6]))
print(sol.hIndex([1, 2, 100]))


class MaximizeDistanceBetweenPointsOnASquare:
    def maxDistance(self, side: int, points: List[List[int]], k: int) -> int:
        def flatten(point: List[int]):
            x, y = point[0], point[1]
            if y == 0:
                return x
            if x == side:
                return side + y
            if y == side:
                return 2*side + (side-x)
            else:  # sure on left bound
                return 3*side + (side-y)
        temp = []
        for point in points:
            temp.append((flatten(point), point[0], point[1]))
        flattern_arr = sorted(temp)
        left = 1
        right = 2*side

        # circular compare - two pointers (O(N) Optimization)
        def isSatisfyManhattan(threshold: int):
            n = len(flattern_arr)
            # Nhân đôi mảng để xử lý vòng tròn thay vì dùng %
            sorted_pts = [(p[1], p[2]) for p in flattern_arr]
            sorted_pts = sorted_pts + sorted_pts
            
            # Dùng Two Pointers tìm điểm tiếp theo thỏa mãn >= threshold
            next_pt = [2 * n] * (2 * n)
            j = 1
            for i in range(2 * n):
                if j <= i:
                    j = i + 1
                while j < 2 * n:
                    x = sorted_pts[i][0] - sorted_pts[j][0]
                    y = sorted_pts[i][1] - sorted_pts[j][1]
                    if abs(x) + abs(y) >= threshold:
                        break
                    j += 1
                next_pt[i] = j
                
            # Tìm bước nhảy index ngắn nhất
            min_diff = 2 * n + 1
            i_min = -1
            for i in range(n):
                if next_pt[i] - i < min_diff:
                    min_diff = next_pt[i] - i
                    i_min = i
                    
            # Pigeonhole Principle: Nếu bước nhảy ngắn nhất vượt quá n // k thì không gom đủ k điểm
            if min_diff > n // k:
                return False
                
            # Chỉ check các điểm bắt đầu trong khoảng bước nhảy ngắn nhất
            for start_idx in range(i_min, next_pt[i_min] + 1):
                start = start_idx % n
                curr = start
                for _ in range(k):
                    curr = next_pt[curr]
                    if curr >= 2 * n:
                        break
                if curr <= start + n:
                    return True
            return False
        while left <= right:
            mid = left + (right-left) // 2
            if isSatisfyManhattan(mid):
                left = mid + 1
            else:
                right = mid - 1
        return right


sol = MaximizeDistanceBetweenPointsOnASquare()
print("MaximizeDistanceBetweenPointsOnASquare")
print(sol.maxDistance(side=2, points=[[0, 2], [2, 0], [2, 2], [0, 0]], k=4))
print(sol.maxDistance(side=2, points=[[0,0],[1,2],[2,0], [2,2], [2,1]], k=4))
print(sol.maxDistance(side=4, points=[
      [4, 4], [3, 4], [2, 0], [4, 3], [4, 0]], k=4))
print(sol.maxDistance(side=15, points=[[0,11],[15,15],[0,0],[0,8],[14,0]], k=4))