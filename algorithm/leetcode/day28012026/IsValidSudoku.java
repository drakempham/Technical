package day28012026;

import java.util.HashSet;
import java.util.Set;

public class IsValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    Set<Character>[] rowSet = new HashSet[9];
    Set<Character>[] colSet = new HashSet[9];
    Set<Character>[] boxSet = new HashSet[9];

    for (int i = 0; i < 9; i++) {
      rowSet[i] = new HashSet<>();
      colSet[i] = new HashSet<>();
      boxSet[i] = new HashSet<>();
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        char val = board[i][j];
        if (val == '.') {
          continue;
        }

        int boxIdx = (i / 3) * 3 + j / 3;
        if (rowSet[i].contains(val) || colSet[j].contains(val) || boxSet[boxIdx].contains(val)) {
          return false;
        }

        rowSet[i].add(val);
        colSet[j].add(val);
        boxSet[boxIdx].add(val);
      }
    }

    return true;
  }
  
  public static void main(String[] args) {
    IsValidSudoku isValidSudoku = new IsValidSudoku();
    char[][] board = {
      { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
      { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
      { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
      { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
    };

    System.out.println(isValidSudoku.isValidSudoku(board)); // true
  }
}
