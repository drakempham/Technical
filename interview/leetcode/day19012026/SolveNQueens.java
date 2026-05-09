import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveNQueens {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }
    // keep three set tracking the existing queen: col, positiveDiagonal (x+y),
    // negativeDiagonal(x-y)
    backtrack(result, board, n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());

    return result;
  }

  // we backtrack each row , so the end will be row == size
  public void backtrack(List<List<String>> result, char[][] boards, int size, int row,
      Set<Integer> col, Set<Integer> posDiagSet, Set<Integer> negaDiaSet) {
    if (row == size) {
      List<String> temp = new ArrayList<>();
      for (char[] board : boards) {
        temp.add(new String(board));
      }
      result.add(temp);
      return;
    }

    // always start from 0 to check queen
    for (int currCol = 0; currCol < size; currCol++) {
      if (col.contains(currCol) || posDiagSet.contains(currCol + row)
          || negaDiaSet.contains(currCol - row)) {
        continue;
      }

      // potential candidate
      col.add(currCol);
      posDiagSet.add(currCol + row);
      negaDiaSet.add(currCol - row);
      boards[row][currCol] = 'Q';

      backtrack(result, boards, size, row + 1, col, posDiagSet, negaDiaSet);

      // reset
      col.remove(currCol);
      posDiagSet.remove(currCol + row);
      negaDiaSet.remove(currCol - row);
      boards[row][currCol] = '.';
    }
  }


  public static void main(String[] args) {
    SolveNQueens solveNQueens = new SolveNQueens();
    System.out.println(solveNQueens.solveNQueens(4));
  }
}
