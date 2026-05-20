import sys

input = sys.stdin.readline

def solve(n, a, b):
    total_valid_segments = 0
    a = [0] + a
    b = [0] + b

    upper_bound = n + 1

    dp = [upper_bound] * (n + 2)

    firstA = [upper_bound] * (n + 2)
    firstB = [upper_bound] * (n + 2)


    for i in range(n, 0, -1):

        firstA[a[i]] = i
        firstB[b[i]] = i

        if a[i] == b[i]:
            k = a[i]

            min_broadcast_day = min(firstA[k + 1], firstB[k + 1])

            if min_broadcast_day == upper_bound:

                dp[i] = upper_bound

            elif a[min_broadcast_day] == k + 1 and b[min_broadcast_day] == k + 1:

                dp[i] = dp[min_broadcast_day]

            else:

                dp[i] = min_broadcast_day

        min_broadcast_day = min(firstA[1], firstB[1])

        if a[min_broadcast_day] == 1 and b[min_broadcast_day] == 1:
          limit = dp[min_broadcast_day]

        elif min_broadcast_day == upper_bound:
            limit = upper_bound
        else:
              limit = min_broadcast_day

        total_valid_segments += limit - i

    return total_valid_segments


if __name__ == "__main__":
    t = int(input())

    for _ in range(t):
        n = int(input())
        a = list(map(int, input().split()))
        b = list(map(int, input().split()))

        print(solve(n, a, b))