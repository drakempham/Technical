# qwqkawaii is registering for 𝑛
#  (𝑛≤50
# ) courses. In the registration system, he can submit a course wish for each course, which indicates his priority for taking that course.

# Course wishes are divided into 𝑘+1
#  (𝑘≤20
# ) priority levels, where level 1
#  is the highest priority and level 𝑘+1
#  is the lowest.

# The first 𝑘
#  wish levels have capacity limits: for each 1≤𝑖≤𝑘
# , at most 𝑎𝑖
#  courses can be assigned wish level 𝑖
# . Note that wish level 𝑘+1
#  has no capacity limit.

# Initially, the 𝑖
# -th course has wish level 𝑏𝑖
# , and it is guaranteed that this initial assignment satisfies all capacity limits. Now qwqkawaii wants to adjust all his courses to wish level 𝑘+1
# . To achieve this, he can apply the following operation at most 1000
#  times:

# Select a course 𝑖
#  (1≤𝑖≤𝑛
# ), then increase 𝑏𝑖
#  by 1
# .
# Note that:

# A course at level 𝑘+1
#  cannot be selected;
# After every single operation, all capacity limits must still be satisfied.
# Your task is to construct a valid adjustment sequence with at most 1000
#  operations, or report that it is impossible.

# Input
# Each test contains multiple test cases. The first line contains the number of test cases 𝑡
#  (1≤𝑡≤50
# ). The description of the test cases follows.

# The first line of each test case contains two integers 𝑛
#  and 𝑘
#  (1≤𝑛≤50
# , 1≤𝑘≤20
# ) — the number of courses and the number of priority levels (excluding the lowest priority level).

# The second line contains 𝑘
#  integers 𝑎1,𝑎2,…,𝑎𝑘
#  (1≤𝑎𝑖≤𝑛
# ) — the capacity limits of the first 𝑘
#  wish levels.

# The third line contains 𝑛
#  integers 𝑏1,𝑏2,…,𝑏𝑛
#  (1≤𝑏𝑖≤𝑘+1
# ) — the initial wish levels of the courses.

# It is guaranteed that the initial assignment satisfies all capacity limits.

# Output
# For each test case, if it is impossible to reach the target state, print a single integer −1
# .

# Otherwise, print the number of operations 𝑚
#  (0≤𝑚≤1000
# ) on the first line of output.

# Then print one line with 𝑚
#  integers 𝑢1,𝑢2,…,𝑢𝑚
#  (1≤𝑢𝑖≤𝑛
# ), denoting that in the 𝑖
# -th operation, you increase the wish level 𝑏𝑢𝑖
#  of course 𝑢𝑖
#  by 1
# .

# Example
# InputCopy
# 4
# 3 2
# 2 2
# 1 2 2
# 4 2
# 2 2
# 3 3 3 3
# 1 1
# 1
# 1
# 5 3
# 1 2 3
# 1 2 4 2 3
# OutputCopy
# 4
# 2 1 3 1
# 0

# 1
# 1
# 8
# 2 4 1 2 1 1 5 4
t = int(input())
for i in range(t):
    n, k = map(int, input().split())
    a = [0] + list(map(int, input().split()))
    b = list(map(int, input().split()))

    levels = [[] for _ in range(k+2)]
    count = [0] * (k+2)

    for idx, level in enumerate(b, start=1):
        levels[level].append(idx)
        count[level] += 1

    ans = []
    while count[k+1] < n:
        moved = False
        for i in range(k, 0, -1):
            if count[i] == 0:
                # no node
                continue

            if i == k or count[i+1] < a[i+1]:
                course_idx = levels[i].pop()
                levels[i + 1].append(course_idx)
                count[i] -= 1
                count[i + 1] += 1
                ans.append(course_idx)
                moved = True
                break

        if not moved:
            print(-1)
            break
    else:
        print(len(ans))
        print(*ans)
