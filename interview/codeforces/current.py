# chalkboard 

import sys
import heapq

def solve():
  input = sys.stdin.read().split()
  
  x = int(input[0])
  q = int(input[1])
  ans = []

  max_heap = [-x]
  min_heap = []
  i = 2
  for _ in range(q):
    a = int(input[i])
    b = int(input[i+1])

    i += 2

    for k in (a,b):
      if -k >= max_heap[0]:
        heapq.heappush(max_heap, -k)
      else:
        heapq.heappush(min_heap, k)

    # max_heap = min_heap + 1
    while len(max_heap) > len(min_heap) + 1:
      heapq.heappush(min_heap, -heapq.heappop(max_heap))
    while len(min_heap) >= len(max_heap):
      heapq.heappush(max_heap, -heapq.heappop(min_heap))
    ans.append(-max_heap[0])
  print("\n".join(map(str, ans)))

if __name__ == "__main__":
  solve()