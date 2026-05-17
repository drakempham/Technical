"""Verifier: runs think.py on a test case, then simulates the output to check correctness."""
import subprocess, sys, random

def verify(n, grid):
    # ── Run solver ──
    inp = f"{n}\n"
    for row in grid:
        inp += " ".join(map(str, row)) + "\n"
    
    result = subprocess.run(
        [sys.executable, "think.py"],
        input=inp, capture_output=True, text=True, timeout=30
    )
    if result.returncode != 0:
        print("STDERR:", result.stderr[:500])
        return False

    lines = result.stdout.strip().split("\n")
    idx = 0
    M = int(lines[idx]); idx += 1

    belts = []
    for _ in range(M):
        parts = list(map(int, lines[idx].split())); idx += 1
        L = parts[0]
        cells = [(parts[1+2*i], parts[2+2*i]) for i in range(L)]
        belts.append(cells)

    T = int(lines[idx]); idx += 1
    ops = []
    for _ in range(T):
        m, d = map(int, lines[idx].split()); idx += 1
        ops.append((m, d))

    # ── Validate belt constraints ──
    assert M <= n*n, f"M={M} > N²={n*n}"
    cell_count = {}
    for bi, belt in enumerate(belts):
        L = len(belt)
        assert L >= 2, f"Belt {bi} length {L} < 2"
        seen = set()
        for x, (r, c) in enumerate(belt):
            assert 0 <= r < n and 0 <= c < n, f"Belt {bi} cell ({r},{c}) out of bounds"
            assert (r,c) not in seen, f"Belt {bi} has duplicate cell ({r},{c})"
            seen.add((r,c))
            cell_count[(r,c)] = cell_count.get((r,c), 0) + 1
            assert cell_count[(r,c)] <= 2, f"Cell ({r},{c}) in >2 belts"
            # Adjacency check
            nr, nc = belt[(x+1) % L]
            assert abs(r-nr) + abs(c-nc) == 1, f"Belt {bi}: ({r},{c})->({nr},{nc}) not adjacent"

    assert T <= 100000, f"T={T} > 10^5"

    # ── Simulate ──
    board = {}
    for r in range(n):
        for c in range(n):
            board[(r,c)] = grid[r][c]

    exit_cell = (0, n//2)
    removed = []
    next_remove = 0

    # Initial check
    if board[exit_cell] == next_remove:
        removed.append(next_remove)
        board[exit_cell] = -1
        next_remove += 1

    for t, (m, d) in enumerate(ops):
        belt = belts[m]
        L = len(belt)
        old = [board[cell] for cell in belt]
        for x in range(L):
            board[belt[(x+d) % L]] = old[x]
        
        # Check removal
        while board[exit_cell] != -1 and board[exit_cell] == next_remove:
            removed.append(next_remove)
            board[exit_cell] = -1
            next_remove += 1

    all_ok = (len(removed) == n*n)
    return all_ok, len(removed), T

# ── Run tests ──
random.seed(42)
n = 4
# Test 1: given sample
grid4 = [
    [5,12,8,3],
    [9,1,14,0],
    [4,15,2,10],
    [7,11,6,13],
]
ok, B, T = verify(n, grid4)
print(f"n={n} sample: B={B}/{n*n}, T={T}, OK={ok}")

# Test 2: n=20 random
n = 20
perm = list(range(n*n))
random.shuffle(perm)
grid20 = [perm[i*n:(i+1)*n] for i in range(n)]
ok, B, T = verify(n, grid20)
print(f"n={n} random: B={B}/{n*n}, T={T}, OK={ok}")

# Test 3: another n=20 random
random.shuffle(perm)
grid20b = [perm[i*n:(i+1)*n] for i in range(n)]
ok, B, T = verify(n, grid20b)
print(f"n={n} random2: B={B}/{n*n}, T={T}, OK={ok}")
