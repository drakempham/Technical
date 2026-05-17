import sys
from collections import deque

MOD = 998244353
C = 26


def mat_mul(a, b):
    n = len(a)
    res = [[0] * n for _ in range(n)]

    for i in range(n):
        row = res[i]

        for k in range(n):
            x = a[i][k]
            if not x:
                continue

            for j in range(n):
                row[j] += x * b[k][j]

        for j in range(n):
            row[j] %= MOD

    return res


def apply(v, mat):
    n = len(v)
    res = [0] * n

    for i in range(n):
        x = v[i]
        if not x:
            continue

        for j in range(n):
            res[j] += x * mat[i][j]

    for i in range(n):
        res[i] %= MOD

    return res


def solve():
    data = sys.stdin.buffer.read().split()
    n = int(data[0])
    k = int(data[1])
    s = data[2:]

    nxt = [[-1] * C]
    fail = [0]
    dead = [False]

    for word in s:
        u = 0

        for ch in word:
            c = ch - 97

            if nxt[u][c] == -1:
                nxt[u][c] = len(nxt)
                nxt.append([-1] * C)
                fail.append(0)
                dead.append(False)

            u = nxt[u][c]

        dead[u] = True

    q = deque()

    for c in range(C):
        v = nxt[0][c]

        if v == -1:
            nxt[0][c] = 0
        else:
            q.append(v)

    while q:
        u = q.popleft()
        dead[u] |= dead[fail[u]]

        for c in range(C):
            v = nxt[u][c]

            if v == -1:
                nxt[u][c] = nxt[fail[u]][c]
            else:
                fail[v] = nxt[fail[u]][c]
                q.append(v)

    pos = [-1] * len(nxt)
    nodes = []

    for i in range(len(nxt)):
        if not dead[i]:
            pos[i] = len(nodes)
            nodes.append(i)

    m = len(nodes)
    trans = [[0] * m for _ in range(m)]

    for u in nodes:
        i = pos[u]

        for c in range(C):
            v = nxt[u][c]

            if not dead[v]:
                trans[i][pos[v]] += 1

    dp = [0] * m
    dp[pos[0]] = 1

    while n:
        if n & 1:
            dp = apply(dp, trans)

        trans = mat_mul(trans, trans)
        n >>= 1

    print(sum(dp) % MOD)


if __name__ == "__main__":
    solve()