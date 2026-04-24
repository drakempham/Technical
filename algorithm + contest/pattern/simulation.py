from typing import List


class NumberOfValidWordsInSentence:
    def countValidWords(self, sentence: str) -> int:
        ans = 0

        def is_valid(word: str) -> bool:
            count_hyphen = 0
            if len(word) == 0:
                return False
            for i, ch in enumerate(word):
                if ch.isdigit():
                    return False
                if ch == '-':
                    count_hyphen += 1
                    if count_hyphen > 1:
                        return False
                    if i == 0 or i == len(word) - 1:
                        return False
                    if not ('a' <= word[i-1] <= 'z' and 'a' <= word[i+1] <= 'z'):
                        return False
                if ch in ['!', '.', ',']:
                    if i != len(word) - 1:
                        return False
            return True

        for word in sentence.split():
            if is_valid(word):
                ans += 1
        return ans


sol = NumberOfValidWordsInSentence()
sentence = "cat and  dog"
print(sol.countValidWords(sentence))


class NextPermutation:
    # 1 2 4 3 -> 1 3 2 4
    def nextPermutation(self, nums: List[int]) -> None:
        n = len(nums)
        pivot = -1  # place we can increase
        for i in range(n-2, -1, -1):
            if nums[i] < nums[i+1]:
                pivot = i
                break
        if pivot == -1:
            nums.reverse()
            return
        for i in range(n-1, pivot, -1):
            if nums[i] > nums[pivot]:
                nums[i], nums[pivot] = nums[pivot], nums[i]
                break
        left, right = pivot+1, len(nums) - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left += 1
            right -= 1


sol = NextPermutation()
nums = [1, 3, 2]
sol.nextPermutation(nums)
print(nums)
