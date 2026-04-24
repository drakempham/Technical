from math import sqrt


class ZigZag:
    def convert(self, s: str, numRows: int) -> str:
        rows = [[] for _ in range(numRows)]
        row = 0
        step = 1
        for ch in s:
            rows[row].append(ch)
            if row == 0:
                step = 1
            elif row == numRows-1:
                step = -1
            row += step
        return "".join(["".join(ele) for ele in rows])


# solution = ZigZag()
# print(solution.convert("PAYPALISHIRING", 3))


class Prime:
    def countPrimes(self, n: int) -> int:
        def is_prime(n: int) -> bool:
            if n <= 1:
                return False
            for i in range(2, int(sqrt(n)) + 1):
                if n % i == 0:
                    return False
            return True
        count = 0
        for i in range(2, n):
            if is_prime(i):
                count += 1
        return count

    def sieve(self, n: int) -> int:
        if n <= 1:
            return 0
        is_prime = [True for _ in range(n)]
        is_prime[0] = is_prime[1] = False
        for i in range(2, int(n**0.5) + 1):
            for j in range(i*i, n, i):
                is_prime[j] = False
        return sum(is_prime)


sol = Prime()
print(sol.sieve(10))
