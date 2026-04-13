from collections import defaultdict, deque
from typing import List


# class NetworkDelayTime:
#     def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
#         graph = defaultdict(list)

#         for a, adjacent, weight in times:
#             graph[a].append((adjacent, weight))
#         dist = [float('inf') for _ in range(n+1)]
#         dist[k] = 0

#         queue = deque([(0, k)])
#         while queue:
#             curr_w, edge = queue.popleft()

#             for adjacent, weight in graph[edge]:
#                 new_w = curr_w + weight
#                 if new_w < dist[adjacent]:
#                     dist[adjacent] = new_w
#                     queue.append((new_w, adjacent))

#         ans = max(dist[1:])
#         return -1 if ans == float('inf') else ans


# sol = NetworkDelayTime()
# print(sol.networkDelayTime([[2, 1, 1], [2, 3, 1], [3, 4, 1]], 4, 2))
class cheapestFlightsWithinKStops:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dest: int, stop: int) -> int:
        graph = defaultdict(list)

        for u, v, price in flights:
            graph[u].append((v, price))
        dist = [float('inf') for _ in range(n+1)]
        dist[src] = 0

        queue = deque([(0, 0, src)])  # price, stop, edge
        while queue:
            curr_price, curr_stop, curr_edge = queue.popleft()
            if curr_stop > stop or curr_edge == dest:
                continue
            for edge, price in graph[curr_edge]:
                new_price = curr_price + price
                if new_price < dist[edge]:
                    dist[edge] = new_price
                    queue.append((new_price, curr_stop + 1, edge))

        return -1 if dist[dest] == float('inf') else dist[dest]

    def findCheapestPriceWithBellman(self, n: int, flights: List[List[int]], src: int, dest: int, k: int) -> int:
        dist = [float('inf')] * n
        dist[src] = 0

        for _ in range(k+1):
            new_dist = dist[:]
            for u, v, w in flights:
                if dist[u] == float('inf'):
                    continue

                new_weight = dist[u] + w
                if new_weight < dist[v]:
                    new_dist[v] = new_weight
            dist = new_dist
        return -1 if dist[dest] == float('inf') else dist[dest]


sol = cheapestFlightsWithinKStops()
# print(sol.findCheapestPriceWithBellman(4, [[0, 1, 100], [1, 2, 100], [
#       2, 0, 100], [1, 3, 600], [2, 3, 200]], 0, 3, 1))
print(sol.findCheapestPriceWithBellman(5, [[1, 0, 5], [2, 1, 5], [
      3, 0, 2], [1, 3, 2], [4, 1, 1], [2, 4, 1]], 2, 0, 2))
