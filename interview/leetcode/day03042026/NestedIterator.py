from typing import List


class NestedInteger:
    def __init__(self, value=None):
        """
        If value is not None and is int -> this is a leaf
        If value is not None and is list -> this is a nested list
        """

        if isinstance(value, int):
            self._integer = value
            self._list = None
        elif isinstance(value, list):
            self._list = value
            self._integer = None
        else:
            self._integer = None
            self._list = []

    def isInteger(self) -> bool:
        return self._integer is not None

    def getInteger(self) -> int:
        return self._integer

    # forward reference
    def getList(self) -> List['NestedInteger']:
        return self._list


# Tree-like recursion
# NestedInteger either can be:
#     + A single integer element
#     + A List of nestedInteger


class NestedIterator:
    def __init__(self, nestedList: List['NestedInteger']):
        self._flattenList = []
        self._currIdx = 0

        def dfs(item: NestedInteger):
            if not item:
                return []
            if item.isInteger() == True:
                self._flattenList.append(item.getInteger())
            else:
                for child in item.getList():
                    dfs(child)
        for elem in nestedList:
            dfs(elem)

    def next(self) -> int:
        element = self._flattenList[self._currIdx]
        self._currIdx += 1
        return element

    def hasNext(self) -> bool:
        return self._currIdx < len(self._flattenList)


inner1 = NestedInteger([NestedInteger(1), NestedInteger(2)])
inner2 = NestedInteger(3)
inner3 = NestedInteger([NestedInteger(4), NestedInteger(5)])

root = NestedInteger([inner1, inner2, inner3])
sol = NestedIterator([root])

while sol.hasNext():
    print(sol.next(), end=" ")
