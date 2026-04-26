

# x + (~x + 1) = 0 -> -x = (~x) + 1
# class NumberOf1Bits:
#     def hammingWeight(self, n: int) -> int:
#         count = 0
#         while n > 0:
#             count += (n & 1)
#             n = n >> 1
#         return count


# sol = NumberOf1Bits()
# print(sol.hammingWeight(11))

from typing import List


class ReverseBits:
    def reverseBits(self, n: int) -> int:
        res = 0
        for _ in range(32):
            res = (res << 1) | (n & 1)
            n >>= 1
        return res


sol = ReverseBits()
print(sol.reverseBits(43261596))


class SingleNumberIII:
    def singleNumber(self, nums: List[int]) -> List[int]:
        # find bit
        xor = 0
        for num in nums:
            xor ^= num
        mask = xor & -xor
        res = [[], []]
        for num in nums:
            if num & mask:
                res[1].append(num)
            else:
                res[0].append(num)

        ans = []
        xor = 0
        for num in res[0]:
            xor ^= num
        ans.append(xor)
        xor = 0
        for num in res[1]:
            xor ^= num
        ans.append(xor)
        return ans


sol = SingleNumberIII()
print(sol.singleNumber([1, 1, 2, 3, 5, 2]))


class MaximumProductOfDistinctWord:
    def maxProduct(self, words: List[str]) -> int:
        bit_conversion = []

        def convert(word: str) -> int:
            mask = 0
            for c in word:
                mask |= (1 << (ord(c) - ord('a')))
            return mask
        for word in words:
            bit_conversion.append(convert(word))
        n = len(words)
        ans = 0
        for i in range(n-1):
            for j in range(i+1, n):
                if (bit_conversion[i] & bit_conversion[j]) == 0:
                    ans = max(
                        ans, len(words[i]) * len(words[j]))
        return ans


sol = MaximumProductOfDistinctWord()
print(sol.maxProduct(["abc", "ab", "cd"]))

# a xor b


class SumOfTwoIntegers:
    def getSum(self, a: int, b: int) -> int:
        mask = (1 << 32) - 1
        max_int = (1 << 31) - 1
        while b != 0:
            # lien tuong den phep cong
            a, b = mask & (a ^ b), mask & ((a & b) << 1)
        return a if a <= max_int else ~(a ^ mask)


sol = SumOfTwoIntegers()
print(sol.getSum(-1, 1))
