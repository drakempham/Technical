# Beginner Friendly 🧠 || Simulation Pattern 🔁 || String Reconstruction 🚀

## Intuition

Only vowels are allowed to move. Every consonant must stay at the same index.

So the useful pattern is:

1. collect information about the characters we are allowed to replace
2. sort those characters by the required rule
3. scan the original string again and fill only the replaceable positions

For this problem, we count each vowel frequency and remember the first index where each vowel appears. Then we sort vowels by:

- higher frequency first
- earlier first occurrence if frequencies are equal

After building the replacement vowel list, we rebuild the string one character at a time.

## Why This Works

The positions of consonants never change, so scanning the original string preserves them automatically.

For vowel positions, we replace them in left-to-right order using the sorted vowel sequence. Since the sorted sequence contains exactly the same vowels with the same counts, every vowel position is filled correctly while following the required order.

## Approach

- **Step 1:** Use a set to identify vowels quickly.
- **Step 2:** Count vowel frequencies with a hash map.
- **Step 3:** Store the first occurrence index of each vowel.
- **Step 4:** Sort unique vowels by `(-frequency, firstIndex)`.
- **Step 5:** Expand the sorted vowels into a replacement list.
- **Step 6:** Rebuild the answer by keeping consonants and replacing vowels.

## Complexity

- Time complexity: `O(n + k log k)`, where `n` is the string length and `k` is the number of distinct vowels.
- Space complexity: `O(n + k)` for the replacement list, result list, and hash maps.

Since there are at most 5 lowercase vowels here, `k` is very small, so this behaves like `O(n)`.

## Code

**Python:**
```python
from collections import defaultdict


class Solution:
    def sortVowels(self, s: str) -> str:
        vowels = set("aeiou")
        freq = defaultdict(int)
        first_occurrence = {}

        for i, ch in enumerate(s):
            if ch in vowels:
                freq[ch] += 1
                if ch not in first_occurrence:
                    first_occurrence[ch] = i

        order = sorted(freq.keys(), key=lambda ch: (-freq[ch], first_occurrence[ch]))
        replacement = []

        for ch in order:
            replacement.extend([ch] * freq[ch])

        answer = []
        vowel_index = 0

        for ch in s:
            if ch not in vowels:
                answer.append(ch)
            else:
                answer.append(replacement[vowel_index])
                vowel_index += 1

        return "".join(answer)
```

**Java:**
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String sortVowels(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        Map<Character, Integer> firstOccurrence = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
                firstOccurrence.putIfAbsent(ch, i);
            }
        }

        List<Character> order = new ArrayList<>(freq.keySet());
        order.sort((a, b) -> {
            int freqCompare = Integer.compare(freq.get(b), freq.get(a));
            if (freqCompare != 0) {
                return freqCompare;
            }
            return Integer.compare(firstOccurrence.get(a), firstOccurrence.get(b));
        });

        List<Character> replacement = new ArrayList<>();
        for (char ch : order) {
            for (int count = 0; count < freq.get(ch); count++) {
                replacement.add(ch);
            }
        }

        StringBuilder answer = new StringBuilder();
        int vowelIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!isVowel(ch)) {
                answer.append(ch);
            } else {
                answer.append(replacement.get(vowelIndex));
                vowelIndex++;
            }
        }

        return answer.toString();
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
```

---

⭐ If this explanation helped you, please upvote 👍 — it motivates me to keep sharing clean and beginner-friendly solutions 🚀
