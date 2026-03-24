n = input()
count = 1
is_diff = False
dangerous = False
for i in range(1, len(n)):
    if n[i] == n[i-1]:
        count += 1
        # sai 1 phat la de nay o ngoai, tuỏng hop array co dung 7 phan tu se ko meet condition
        if count == 7:
            dangerous = True
            break
    else:
        count = 1

if dangerous:
    print("YES")
else:
    print("NO")
