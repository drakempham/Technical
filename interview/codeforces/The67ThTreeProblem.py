def The67ThTreeProblem():
    t = int(input())

    for _ in range(t):
        x, y = map(int, input().split())
        n = x + y

        if x > y or (x == 0 and n % 2 == 0):
            print("NO")
            continue

        print("YES")

        cur = 2

        if n % 2 == 1:
            pair_count = x
        else:
            pair_count = x - 1

        for _ in range(pair_count):
            print(1, cur)
            print(cur, cur + 1)
            cur += 2

        while cur <= n:
            print(1, cur)
            cur += 1


The67ThTreeProblem()
