class VerifyPreorderSerialization:
    def isValidSerialization(self, preorder: str) -> bool:
        if preorder[0] == '#' and len(preorder) > 1:
            return False
        arr = preorder.split(',')
        total_leaf = 1
        last_ele = preorder[0]
        for ele in arr:
            if total_leaf == 0 and last_ele == '#':
                return False
            if ele.isdigit():
                total_leaf += 1
            else:
                total_leaf -= 1
                if total_leaf < 0:
                    return False
            last_ele = ele
        return total_leaf == 0

    def isValidSerializationWithSlots(self, preorder: str) -> bool:
        arr = preorder.split(',')
        slots = 1
        for ele in arr:
            slots -= 1
            if slots < 0:
                return False
            if ele.isdigit():
                slots += 2
        return slots == 0


sol = VerifyPreorderSerialization()
print(sol.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"))
