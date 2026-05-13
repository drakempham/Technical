import sys

read = sys.stdin.readline
MOD = 998244353

n = int(read())
d = list(map(int, read().split()))

rev = [1] * (n + 1)
for x in range(1, n + 1):
    rev[x] = pow(x, MOD - 2, MOD)

bag = {n - 1: 1, n: 1}
factor = 1
seal = n - 1

while seal >= 2:
    nxt_left = seal - 1
    old_gap = d[seal - 1]
    free_pick = n - seal
    need_pos = nxt_left + d[nxt_left - 1]

    if d[nxt_left - 1] == old_gap:
        old_val = bag.get(need_pos, 0)
        factor = factor * free_pick % MOD

        if old_val:
            add = old_val * rev[free_pick] % MOD
            bag[need_pos] = (bag.get(need_pos, 0) + add) % MOD
            bag[nxt_left] = (bag.get(nxt_left, 0) + add) % MOD
    else:
        old_val = bag.get(need_pos, 0)

        if old_val:
            bag = {need_pos: old_val,
                   nxt_left: old_val
                   }
        else:
            bag = {}

    seal = nxt_left

ans = factor * sum(bag.values()) % MOD
print(ans)
