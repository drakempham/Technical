class Solution:
    # def numTilePossibilities(self, tiles: str) -> int:
    #     counter = {}
    #     for tile in tiles:
    #         counter[tile] = counter.get(tile, 0) + 1
    #     self.total = 0

    #     def backtrack():
    #         for ele in counter:
    #             if counter[ele] > 0:
    #                 self.total += 1
    #                 counter[ele] -= 1
    #                 backtrack()
    #                 counter[ele] += 1

    #     backtrack()

    #     return self.total
    def numTilePossibilities(self, tiles: str) -> int:
        counter = {}
        for tile in tiles:
            counter[tile] = counter.get(tile, 0) + 1

        def backtrack() -> int:
            for ele in counter:
                if counter[ele] > 0:
                    total = 1
                    counter[ele] -= 1
                    total += backtrack()
                    counter[ele] += 1
            return 0

        return backtrack()


sol = Solution()
print(sol.numTilePossibilities("AAB"))
