class PrefixSum2D:
    def __init__(self, matrix):
        M = len(matrix)
        N = len(matrix[0])
        self.P = [[0] * (N + 1) for _ in range(M + 1)]

        for i in range(1, M + 1):
            for j in range(1, N + 1):
                self.P[i][j] = (matrix[i-1][j-1]
                                + self.P[i-1][j]
                                + self.P[i][j-1]
                                - self.P[i-1][j-1])

    def sumOfMtrx(self, r1: int, c1: int, r2: int, c2: int) -> int:
        """Return sum of submatrix [r1..r2][c1..c2] (0-indexed, inclusive)."""
        r1 += 1; r2 += 1; c1 += 1; c2 += 1
        return (self.P[r2][c2]
                - self.P[r1-1][c2]
                - self.P[r2][c1-1]
                + self.P[r1-1][c1-1])


if __name__ == "__main__":
    # Simple test case
    matrix = [
        [3, 0, 1, 4, 2],
        [5, 6, 3, 2, 1],
        [1, 2, 0, 1, 5],
        [4, 1, 0, 1, 7],
        [1, 0, 3, 0, 5]
    ]
    ps = PrefixSum2D(matrix)
    
    # Test 1 (r1=2, c1=1, r2=4, c2=3)
    # Expected submatrix:
    # 2 0 1
    # 1 0 1
    # 0 3 0
    # Sum: 8
    assert ps.sumOfMtrx(2, 1, 4, 3) == 8, f"Expected 8, got {ps.sumOfMtrx(2, 1, 4, 3)}"
    
    # Test 2 (r1=1, c1=1, r2=2, c2=2)
    # Expected submatrix:
    # 6 3
    # 2 0
    # Sum: 11
    assert ps.sumOfMtrx(1, 1, 2, 2) == 11, f"Expected 11, got {ps.sumOfMtrx(1, 1, 2, 2)}"
    
    # Test 3 (r1=1, c1=2, r2=2, c2=4)
    # Expected submatrix:
    # 3 2 1
    # 0 1 5
    # Sum: 12
    assert ps.sumOfMtrx(1, 2, 2, 4) == 12, f"Expected 12, got {ps.sumOfMtrx(1, 2, 2, 4)}"

    print("All test cases passed successfully!")
