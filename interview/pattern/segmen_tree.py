class SegmentTree:
    def __init__(self, nums):
        self.n = len(nums)
        self.tree = [0] * (4 * self.n)
        self.build(1, 0, self.n - 1, nums)

    def build(self, node, left, right, nums):
        if left == right:
            self.tree[node] = nums[left]
            return

        mid = (left + right) // 2
        self.build(node * 2, left, mid, nums)
        self.build(node * 2 + 1, mid + 1, right, nums)

        self.tree[node] = self.tree[node * 2] + self.tree[node * 2 + 1]

    def update(self, node, left, right, index, value):
        if left == right:
            self.tree[node] = value
            return

        mid = (left + right) // 2
        if index <= mid:
            self.update(node * 2, left, mid, index, value)
        else:
            self.update(node * 2 + 1, mid + 1, right, index, value)

        self.tree[node] = self.tree[node * 2] + self.tree[node * 2 + 1]

    def query(self, node, left, right, ql, qr):
        if qr < left or right < ql:
            return 0

        if ql <= left and right <= qr:
            return self.tree[node]

        mid = (left + right) // 2
        left_sum = self.query(node * 2, left, mid, ql, qr)
        right_sum = self.query(node * 2 + 1, mid + 1, right, ql, qr)

        return left_sum + right_sum
