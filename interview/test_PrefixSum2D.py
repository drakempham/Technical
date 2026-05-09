import pytest
from PrefixSum2D import PrefixSum2D


# ─── Fixtures ────────────────────────────────────────────────────────────────

@pytest.fixture
def mat3x4():
    return [
        [1,  2,  3,  4],
        [5,  6,  7,  8],
        [9, 10, 11, 12],
    ]

@pytest.fixture
def ps3x4(mat3x4):
    return PrefixSum2D(mat3x4)


# ─── Build tests ─────────────────────────────────────────────────────────────

def test_prefix_table_shape(mat3x4):
    ps = PrefixSum2D(mat3x4)
    assert len(ps.P) == 4          # M+1 rows
    assert len(ps.P[0]) == 5       # N+1 cols

def test_prefix_table_border_zeros(mat3x4):
    ps = PrefixSum2D(mat3x4)
    for j in range(5):
        assert ps.P[0][j] == 0     # top sentinel row
    for i in range(4):
        assert ps.P[i][0] == 0     # left sentinel col


# ─── Single-cell queries ─────────────────────────────────────────────────────

def test_single_cell_top_left(ps3x4):
    assert ps3x4.sumOfMtrx(0, 0, 0, 0) == 1

def test_single_cell_bottom_right(ps3x4):
    assert ps3x4.sumOfMtrx(2, 3, 2, 3) == 12

def test_single_cell_middle(ps3x4):
    assert ps3x4.sumOfMtrx(1, 1, 1, 1) == 6


# ─── Full-matrix query ────────────────────────────────────────────────────────

def test_full_matrix(ps3x4):
    # 1+2+...+12 = 78
    assert ps3x4.sumOfMtrx(0, 0, 2, 3) == 78


# ─── Sub-matrix queries ───────────────────────────────────────────────────────

def test_top_left_2x2(ps3x4):
    # [[1,2],[5,6]] → 14
    assert ps3x4.sumOfMtrx(0, 0, 1, 1) == 14

def test_bottom_right_2x2(ps3x4):
    # [[7,8],[11,12]] → 38
    assert ps3x4.sumOfMtrx(1, 2, 2, 3) == 38

def test_single_row(ps3x4):
    # row 1: 5+6+7+8 = 26
    assert ps3x4.sumOfMtrx(1, 0, 1, 3) == 26

def test_single_column(ps3x4):
    # col 2: 3+7+11 = 21
    assert ps3x4.sumOfMtrx(0, 2, 2, 2) == 21

def test_middle_block(ps3x4):
    # rows 0-1, cols 1-2: [[2,3],[6,7]] → 18
    assert ps3x4.sumOfMtrx(0, 1, 1, 2) == 18


# ─── 1×1 matrix ──────────────────────────────────────────────────────────────

def test_1x1_matrix():
    ps = PrefixSum2D([[42]])
    assert ps.sumOfMtrx(0, 0, 0, 0) == 42


# ─── Single row / single column matrices ─────────────────────────────────────

def test_single_row_matrix():
    ps = PrefixSum2D([[1, 2, 3, 4, 5]])
    assert ps.sumOfMtrx(0, 0, 0, 4) == 15
    assert ps.sumOfMtrx(0, 1, 0, 3) == 9

def test_single_col_matrix():
    ps = PrefixSum2D([[1], [2], [3], [4]])
    assert ps.sumOfMtrx(0, 0, 3, 0) == 10
    assert ps.sumOfMtrx(1, 0, 2, 0) == 5


# ─── Negative numbers ────────────────────────────────────────────────────────

def test_negative_values():
    ps = PrefixSum2D([[-1, -2], [-3, -4]])
    assert ps.sumOfMtrx(0, 0, 1, 1) == -10
    assert ps.sumOfMtrx(0, 0, 0, 0) == -1

def test_mixed_sign_values():
    ps = PrefixSum2D([[1, -1], [-1, 1]])
    assert ps.sumOfMtrx(0, 0, 1, 1) == 0
    assert ps.sumOfMtrx(0, 0, 0, 1) == 0  # row 0: 1+(-1) = 0


# ─── All-zeros matrix ────────────────────────────────────────────────────────

def test_all_zeros():
    ps = PrefixSum2D([[0, 0], [0, 0]])
    assert ps.sumOfMtrx(0, 0, 1, 1) == 0


# ─── Large values ────────────────────────────────────────────────────────────

def test_large_values():
    ps = PrefixSum2D([[10**6, 10**6], [10**6, 10**6]])
    assert ps.sumOfMtrx(0, 0, 1, 1) == 4 * 10**6


# ─── Idempotency: multiple queries on same instance ──────────────────────────

def test_multiple_queries_independent(ps3x4):
    a = ps3x4.sumOfMtrx(0, 0, 2, 3)
    b = ps3x4.sumOfMtrx(0, 0, 0, 0)
    c = ps3x4.sumOfMtrx(0, 0, 2, 3)
    assert a == c == 78
    assert b == 1
