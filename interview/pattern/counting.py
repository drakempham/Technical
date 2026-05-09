class FurthestDistanceFromOrigin:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        right_counter = 0
        left_counter = 0
        for move in moves:
            if move == 'L':
                left_counter +=1
            elif move == 'R':
                right_counter +=1
        underline_counter = len(moves) - left_counter - right_counter
        return underline_counter + abs(left_counter - right_counter)

sol = FurthestDistanceFromOrigin()
print(sol.furthestDistanceFromOrigin("LRRDDLRR")) 