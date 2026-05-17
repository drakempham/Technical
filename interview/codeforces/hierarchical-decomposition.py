# def solve():
#     n = int(input().strip())
    
#     grid = []
#     for _ in range(n):
#         row = list(map(int, input().split()))
#         grid.append(row)
        
#     cycle = []
#     for i in range(0,n):
#         if i == 0:
#             for j in range(n):
#                 cycle.append((i,j))
#             continue
        
#         if i % 2 == 1:
#             for j in range(n-1, 0, -1):
#                 cycle.append((i,j))
#         else:
#             for j in range(1,n):
#                 cycle.append((i,j))

#     for i in range(n-1,0,-1):
#         cycle.append((i,0))
        
#     #  Khởi tạo trạng thái ban đầu
#     pos = {}
#     for idx, (r,c) in enumerate(cycle):
#         val = grid[r][c]
#         pos[val] = idx
        
#     exit_idx = n//2
#     operations = []
    
#     # Simulation
#     total_boxes = n * n
#     for target_box in range(total_boxes):
#         current_pos = pos[target_box]
        
#         if current_pos == exit_idx:
#             continue
            
#         dist_plus = (exit_idx - current_pos) % total_boxes
#         dist_minus = (current_pos - exit_idx) % total_boxes
        
#         if dist_plus <= dist_minus:
#             direction = 1
#             steps = dist_plus
#         else:
#             direction = -1
#             steps = dist_minus
            
#         for _ in range(steps):
#             operations.append(f"0 {direction}")
            
#         # Update
#         for b in range(target_box + 1, total_boxes):
#             val = pos[b] + steps if direction == 1 else pos[b] - steps
#             pos[b] = val % total_boxes

#     print(1)
    
#     belt_info = [str(len(cycle))]
#     for r, c in cycle:
#         belt_info.append(str(r))
#         belt_info.append(str(c))
#     print(" ".join(belt_info))
    
#     print(len(operations))
#     print("\n".join(operations))

# if __name__ == '__main__':
#     solve()



# ban optimize

# 0   1   2  ...  9 | 10 ... 18 19
#      +-------------------+----------------+
#  H0  | [----Nhánh Trái--]X[--Nhánh Phải--]|
#  H1  | [----Nhánh Trái--]X[--Nhánh Phải--]|
#      +-------------------+----------------+
#  H2  | [----Nhánh Trái--]X[--Nhánh Phải--]|
#  H3  | [----Nhánh Trái--]X[--Nhánh Phải--]|
#      +-------------------+----------------+
#  ... |        ...        |       ...      |
#      +-------------------+----------------+
# H18  | [----Nhánh Trái--]X[--Nhánh Phải--]|
# H19  | [----Nhánh Trái--]X[--Nhánh Phải--]|
#      +-------------------+----------------+
#         X = Giao điểm (Trạm trung chuyển) nằm trên Trục Chính (Cột 9 và 10)
#         Exit nằm ở (0, 10)

import sys

def solve():
    input_data = sys.stdin.read().split()
    n = int(input_data[0])
    if not input_data:
        return
    grid = []
    idx = 1
    for i in range(n):
        row = []
        for j in range(n):
            row.append(int(input_data[idx]))
            idx += 1
        grid.append(row)
        
    belts = []
    
    bb = []
    for i in range(n):
        bb.append((i, 10))
    for i in range(n-1, -1, -1):
        bb.append((i, 9))
    belts.append(bb)
    
    for i in range(0, n, 2):
        f = []
        for j in range(10):
            f.append((i, j))
        for j in range(9, -1, -1):
            f.append((i+1, j))
        belts.append(f)
        
    for i in range(0, n, 2):
        f = []
        for j in range(10, n):
            f.append((i, j))
        for j in range(n-1, 9, -1):
            f.append((i+1, j))
        belts.append(f)
        
    pos = {}
    for r in range(n):
        for c in range(n):
            pos[grid[r][c]] = (r, c)
            
    ops = []
    total_boxes = n * n
    
    def rotate(b_idx, steps, direction):
        if steps == 0:
            return
        b = belts[b_idx]
        lb = len(b)
        vals = [grid[r][c] for r, c in b]
        
        if direction == 1:
            s = steps % lb
            vals = vals[-s:] + vals[:-s]
        else:
            s = steps % lb
            vals = vals[s:] + vals[:s]
            
        for i, (r, c) in enumerate(b):
            grid[r][c] = vals[i]
            pos[vals[i]] = (r, c)
            
        for _ in range(steps):
            ops.append(f"{b_idx} {direction}")

    for target_box in range(total_boxes):
        r, c = pos[target_box]
        if (r, c) == (0, 10):
            continue
            
        if c < 9:
            b_idx = 1 + r // 2
            idx = belts[b_idx].index((r, c))
            lb = len(belts[b_idx])
            
            t1 = belts[b_idx].index((r, 9))
            t2 = belts[b_idx].index((r + 1 if r % 2 == 0 else r - 1, 9))
            
            dist_v1 = (t1 - idx) % lb
            dist_v1_minus = (idx - t1) % lb
            dist_v2 = (t2 - idx) % lb
            dist_v2_minus = (idx - t2) % lb
            
            best = min(dist_v1, dist_v1_minus, dist_v2, dist_v2_minus)
            if best == dist_v1:
                rotate(b_idx, best, 1)
            elif best == dist_v1_minus:
                rotate(b_idx, best, -1)
            elif best == dist_v2:
                rotate(b_idx, best, 1)
            else:
                rotate(b_idx, best, -1)
                
        elif c > 10:
            b_idx = 11 + r // 2
            idx = belts[b_idx].index((r, c))
            lb = len(belts[b_idx])
            
            t1 = belts[b_idx].index((r, 10))
            t2 = belts[b_idx].index((r + 1 if r % 2 == 0 else r - 1, 10))
            
            dist_plus_1 = (t1 - idx) % lb
            dist_minus_1 = (idx - t1) % lb
            dist_plus_2 = (t2 - idx) % lb
            dist_minus_2 = (idx - t2) % lb
            
            best = min(dist_plus_1, dist_minus_1, dist_plus_2, dist_minus_2)
            if best == dist_plus_1:
                rotate(b_idx, best, 1)
            elif best == dist_minus_1:
                rotate(b_idx, best, -1)
            elif best == dist_plus_2:
                rotate(b_idx, best, 1)
            else:
                rotate(b_idx, best, -1)
                
        r, c = pos[target_box]
        if (r, c) != (0, 10):
            b_idx = 0
            idx = belts[b_idx].index((r, c))
            lb = len(belts[b_idx])
            t = belts[b_idx].index((0, 10))
            
            dist_plus = (t - idx) % lb
            dist_minus = (idx - t) % lb
            
            if dist_plus <= dist_minus:
                rotate(b_idx, dist_plus, 1)
            else:
                rotate(b_idx, dist_minus, -1)
                
    print(len(belts))
    for b in belts:
        belt_info = [str(len(b))]
        for r, c in b:
            belt_info.append(str(r))
            belt_info.append(str(c))
        print(" ".join(belt_info))
        
    print(len(ops))
    print("\n".join(ops))

if __name__ == '__main__':
    solve()