from typing import List
class SparseTable2D:
    def __init__(self, matrix: List[List[int]]):
        self.m = len(matrix)
        self.n = len(matrix[0])

        self.ceil_logm = self.m.bit_length()
        self.ceil_logn = self.n.bit_length()

        # st[kr][kc][r][c]
        # max rectangle from (r, c)
        # height = 2^kr
        # width  = 2^kc
        st = [
            [
            [[0]*self.n for _ in range(self.m)]
            for _ in range(self.ceil_logn)
            ]
            for _ in range(self.ceil_logm)
        ]

        self.st = st
        
        for i in range(self.m):
            for j in range(self.n):
                st[0][0][i][j] = matrix[i][j]
        
        # xet tren cot truoc, length tu 1 -> 2^ceil_logn
        # [(1x2), (1x4), (1x8)]
        for kc in range(1, self.ceil_logn):
            length = 2**kc
            half = 2**(kc-1)
            for i in range(self.m):
                for j in range(self.n - length +1):
                    st[0][kc][i][j] = max(st[0][kc-1][i][j],
                    st[0][kc-1][i][j + half])
        
        # xet tren hang, row_length tu 1 -> 2^ceil_logm, row_col tu 0 -> 2^ceil_logn
        # [(2x1),(2x2), (2x4), 2x8)], [(4x1), ]
        for kr in range(1, self.ceil_logm):
            length_kr = 2**kr
            half_kr = 2**(kr-1)
            for kc in range(self.ceil_logn):
                length_kc = 2**kc
                for i in range(self.m - length_kr + 1):
                    for j in range(self.n - length_kc + 1):
                        st[kr][kc][i][j] = max(st[kr-1][kc][i][j], 
                        st[kr-1][kc][i + half_kr][j])


    def query(self, r1: int, c1: int, r2: int, c2: int):
        if r1 > r2 or c1 > c2:
            return 0
        
        height= r2 - r1 + 1
        width = c2 - c1 + 1

        kc = width.bit_length() - 1
        kr = height.bit_length() -1

        block_c = 2**kc
        block_r = 2**kr

        return max(
            self.st[kr][kc][r1][c1],
            self.st[kr][kc][r2 - block_r + 1][c1],
            self.st[kr][kc][r1][c2-block_c + 1],
            self.st[kr][kc][r2-block_r + 1][c2-block_c + 1]
        )
class Solution:
    def countLocalMaximums(self, matrix: List[List[int]]) -> int:
        rows = len(matrix)
        cols = len(matrix[0])
        sp = SparseTable2D(matrix)
        ans = 0

        for r in range(rows):
            for c in range(cols):
                x = matrix[r][c]
                if x == 0:
                    continue

                top = r - x
                bottom = r + x
                left = c - x
                right = c + x

                v_r1 = max(0, top)
                v_r2 = min(rows - 1, bottom)
                v_c1 = max(0, left + 1)
                v_c2 = min(cols - 1, right - 1)

                h_r1 = max(0, top + 1)
                h_r2 = min(rows - 1, bottom - 1)
                h_c1 = max(0, left)
                h_c2 = min(cols - 1, right)

                max_vertical = sp.query(v_r1, v_c1, v_r2, v_c2)
                max_horizontal = sp.query(h_r1, h_c1, h_r2, h_c2)
                
                best = max(max_vertical, max_horizontal)

                if best <= x:
                    ans += 1

        return ans



