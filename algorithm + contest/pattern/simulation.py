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
