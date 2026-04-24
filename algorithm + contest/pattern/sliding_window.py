from collections import Counter, defaultdict


class LongestSubStringAtMostTwoDisticnt:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        left, right = 0, 0
        ans = 1
        counter = {}
        while right < len(s):
            counter[s[right]] = counter.get(s[right], 0) + 1
            while len(counter) > 2:
                counter[s[left]] -= 1
                if counter[s[left]] == 0:
                    counter.pop(s[left])
                left += 1

            ans = max(ans, right - left + 1)
            right += 1
        return ans

    def lengthOfLongestSubStringTwoDistinctWithCounter(self, s: str):
        left, right = 0, 0
        ans = 1
        counter = Counter()
        while right < len(s):
            counter[s[right]] += 1
            while len(counter) > 2:
                counter[s[left]] -= 1
                if counter[s[left]] == 0:
                    counter.pop(s[left])
                left += 1

            ans = max(ans, right - left + 1)
            right += 1
        return ans


sol = LongestSubStringAtMostTwoDisticnt()
print(sol.lengthOfLongestSubStringTwoDistinctWithCounter("cbcaa"))
