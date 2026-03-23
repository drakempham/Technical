from collections import defaultdict


scores = [('math', 98), ('science', 72)]
grouped = defaultdict(list)
for subject, score in scores:
    grouped[subject].append(score)


print(grouped)
