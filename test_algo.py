from typing import List

def check_algo(side, points, k, T):
    def flatten(point: List[int]):
        x, y = point[0], point[1]
        if y == 0: return x
        if x == side: return side + y
        if y == side: return 2*side + (side-x)
        return 3*side + (side-y)

    temp = []
    for p in points:
        temp.append((flatten(p), p[0], p[1]))
    temp.sort()
    
    n = len(temp)
    arr = temp + temp
    
    next_pt = [-1] * (2 * n)
    j = 1
    for i in range(2 * n):
        if j <= i: j = i + 1
        while j < 2 * n:
            x = arr[i][1] - arr[j][1]
            y = arr[i][2] - arr[j][2]
            if abs(x) + abs(y) >= T:
                break
            j += 1
        next_pt[i] = j

    # find min diff
    min_diff = 2 * n + 1
    i_min = -1
    for i in range(n):
        if next_pt[i] - i < min_diff:
            min_diff = next_pt[i] - i
            i_min = i
            
    if min_diff > n:
        return False
        
    for start in range(i_min, next_pt[i_min] + 1):
        if start >= n: break
        curr = start
        for _ in range(k):
            curr = next_pt[curr]
            if curr >= 2 * n:
                break
        if curr <= start + n:
            return True
            
    return False

print(check_algo(15, [[0,11],[15,15],[0,0],[0,8],[14,0]], 4, 15))
print("T=11:", check_algo(15, [[0,11],[15,15],[0,0],[0,8],[14,0]], 4, 11))
print("T=12:", check_algo(15, [[0,11],[15,15],[0,0],[0,8],[14,0]], 4, 12))
