
import random


class Solution:
    def rand7(self):
        return random.randint(1, 7)

    def rand10(self):
        return self.rand7() + random.randint(0, 10 - 7)
