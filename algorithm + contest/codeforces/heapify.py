# x = ...a b c 1 0 0 0    (bit thấp nhất là vị trí có giá trị 1 đầu tiên từ phải)
# -x = ...ā b̄ c̄ 1 0 0 0    (các bit bên trái bị đảo, bit thấp nhất và các số 0 bên phải giữ nguyên)
# x & -x = 0 0 0 1 0 0 0    (chỉ còn bit thấp nhất)
# x // (x&-x) -> the largest 2 that it can divide
def chain_input(n: int):
    return n // (n & -n)


def get_odd_root(n: int):
    while n % 2 == 0:
        n //= 2
    return n


n = int(input())
for i in range(n):
    size = int(input())
    arr = list(map(int, input().split()))
    ok = True
    for i in range(len(arr)):
        # if chain_input(i+1) != chain_input(arr[i]):
        if get_odd_root(i+1) != get_odd_root(arr[i]):
            ok = False
            break

    if ok:
        print("YES")
    else:
        print("NO")
