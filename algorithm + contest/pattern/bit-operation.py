# class NumberOf1Bits:
#     def hammingWeight(self, n: int) -> int:
#         count = 0
#         while n > 0:
#             count += (n & 1)
#             n = n >> 1
#         return count


# sol = NumberOf1Bits()
# print(sol.hammingWeight(11))

class ReverseBits:
    def reverseBits(self, n: int) -> int:
        res = 0
        for _ in range(32):
            res = (res << 1) | (n & 1)
            n >>= 1
        return res


sol = ReverseBits()
print(sol.reverseBits(43261596))
