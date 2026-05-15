from os import name
from typing import List
class SQL:
    def __init__(self, names: List[str], columns: List[int]):
      self.table = {}

      for name, column in zip(names, columns):
        self.table[name] = {
          "columns": column,
          "next_id": 1,
          "rows": {}
        }
      print(self.table)
    def ins(self, name: str, row: List[str]) -> bool:
      if name not in self.table:
        return False
      if len(row) != self.table[name]["columns"]:
        return False
      curr_id = self.table[name]["next_id"]

      self.table[name]["rows"][curr_id] = row
      self.table[name]["next_id"] = curr_id + 1

      return True

      print(f"insert {self.table[name]}")
    def rmv(self, name: str, rowId: int) -> None:
      if name not in self.table:
          return

      if rowId not in self.table[name]["rows"]:
        return
      
      del self.table[name]["rows"][rowId]
    
    def sel(self, name: str, rowId: int, columnId: int):
      if name not in self.table:
          return "<null>"
      if rowId not in self.table[name]["rows"] or columnId <=0 or columnId > self.table[name]["columns"]:
        return "<null>"
      return self.table[name]["rows"][rowId][columnId-1]
    
    def exp(self, name: str) -> List[str]:
      if name not in self.table:
          return []
      ans = []
      for rowId in sorted(self.table[name]["rows"].keys()):
        val = self.table[name]["rows"][rowId]
        ans.append(f"{rowId},{','.join(val)}")

      return ans

sol = SQL(["one","two","three"],[2,3,1])
sol.ins("two", ["first","second","third"])
print(sol.sel("two", 1,3))
sol.ins("two",["fourth","fifth","sixth"])
print(sol.exp("two"))
print(sol.rmv("two", 1))