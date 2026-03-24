n = int(input())
for i in range(n):
    len_arr = int(input())
    arr = sorted(list(set(map(int, input().split()))))
    longest_subsequence = 1
    curr_len = 1
    for i in range(1, len(arr)):
        if arr[i] == arr[i-1] + 1:
            curr_len += 1
        else:
            longest_subsequence = max(longest_subsequence, curr_len)
            curr_len = 1
    longest_subsequence = max(longest_subsequence, curr_len)
    print(longest_subsequence)
