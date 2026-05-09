from typing import List


class CarPooling:
    # if n small
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        diff = [0] * 1001
        for passengers, start, stop in trips:
            diff[start] += passengers
            diff[stop] -= passengers
        total = 0
        for i in range(len(diff)):
            total += diff[i]
            if total > capacity:
                return False
        return True
    # if n large

    def carPooling2(self, trips: List[List[int]], capacity: int) -> bool:
        events = []
        for passenger, start, stop in trips:
            events.append((start, passenger))
            events.append((stop, -passenger))
        events.sort()

        total = 0
        for _, passenger in events:
            total += passenger
            if total > capacity:
                return False
        return True


sol = CarPooling()
print(sol.carPooling2([[2, 1, 5], [3, 3, 7]], 4))
