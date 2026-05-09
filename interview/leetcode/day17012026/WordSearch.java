public class WordSearch {
  // find path = dfs + graph search
  public boolean exist(char[][] board, String word) {
    int row = board.length;
    int col = board[0].length;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        // Start from the first meeting
        if (board[i][j] == word.charAt(0)) {
          if (dfs(board, word, row, col, i, j, 0)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  boolean dfs(char[][] board, String word, int row, int col, int currRow, int currCol, int idx) {
    if (idx == word.length()) {
      return true;
    }
    if (currRow < 0 || currRow >= row || currCol < 0 || currCol >= col
        || board[currRow][currCol] != word.charAt(idx)) {
      return false;
    }

    // mark the column at visited
    char temp = board[currRow][currCol];
    board[currRow][currCol] = '*';

    boolean isExist = dfs(board, word, row, col, currRow - 1, currCol, idx + 1) ||
        dfs(board, word, row, col, currRow + 1, currCol, idx + 1) ||
        dfs(board, word, row, col, currRow, currCol - 1, idx + 1) ||
        dfs(board, word, row, col, currRow, currCol + 1, idx + 1);

    board[currRow][currCol] = temp;

    return isExist;
  }

  public static void main(String[] args) {
    WordSearch wordSearch = new WordSearch();
    // char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    // String word = "ABCCED";
    // System.out.println(wordSearch.exist(board, word));
    char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    String word = "ABCB";
    System.out.println(wordSearch.exist(board, word));
  }
}
