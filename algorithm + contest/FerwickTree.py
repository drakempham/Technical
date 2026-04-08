class FenwickTree:
    def __init__(self, n):
        self.ft = [0] * (n + 1)
        self.n = n

    def build_from_list(self, arr):
        n = len(arr)
        ft = self.__class__(n)
        for i in range(1,n+1):
            ft.ft[i] += arr[i-1]
            parent = i + (i & (-i))
            if parent <= n:
                ft.ft[parent] += ft.ft[i]
        return ft
    
    def update(self, i, num):
        self.ft[i] += num
        while i <= self.n:
            i += i & (-i)
            if i > self.n:
                break
            self.ft[i] += num
        return self.ft[i]
    
    def query(self, i):
        sum = 0
        while i > 0:
            sum += self.ft[i]
            i -= i & (-i)
        return sum

    def query_range(self, l, r):
        return self.query(r) - self.query(l-1)


# init array 
arr = [1,2,3,4,5,6,7,8,9,10]
sol = FenwickTree(len(arr))
ft = sol.build_from_list(arr)
print(ft.query(5))
print(ft.query_range(2,5))