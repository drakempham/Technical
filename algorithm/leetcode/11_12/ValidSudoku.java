import java.util.HashSet;

public class ValidSudoku {
  // Each cell represnet at row i, col j, box (i/3, j /3)
  public boolean isValidSudoku1(char[][] board) {
    var isValid = new HashSet<String>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        var val = board[i][j];
        if (val == '.') {
          continue;
        }
        var rowText = "The board is " + val + " value in " + i + " row";
        var colText = "The board is " + val + " value in " + j + " col";
        var boxText = "The board is " + val + " value in " + (i / 3) + "," + (j / 3) + " box";

        if (!isValid.add(rowText) || !isValid.add(colText) || !isValid.add(boxText)) {
          return false;
        }
      }
    }

    return true;
  }

  // use hashSet to track row, col, box
  @SuppressWarnings("unchecked")
  public boolean isValidSudoku2(char[][] board) {
    var rows = new HashSet[board.length];
    var cols = new HashSet[board[0].length];
    var boxes = new HashSet[board.length];

    for (int i = 0; i < board.length; i++) {
      rows[i] = new HashSet<Character>();
      cols[i] = new HashSet<Character>();
      boxes[i] = new HashSet<Character>();
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        var val = board[i][j];
        if (val == '.') {
          continue;
        }

        if (!rows[i].add(val) || !cols[j].add(val) || !boxes[(i / 3) * 3 + j / 3].add(val)) {
          return false;
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidSudoku sol = new ValidSudoku();
    // char[][] board = {
    // { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
    // { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
    // { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
    // { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
    // { '4', '.', '6', '8', '.', '3', '.', '.', '1' },
    // { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
    // { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
    // { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
    // { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    // };
    // var result = sol.isValidSudoku1(board);
    // System.out.println(result);

    char[][] board2 = {
        { '8', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '6', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };
    var result2 = sol.isValidSudoku2(board2);
    System.out.println(result2);
  }
}
