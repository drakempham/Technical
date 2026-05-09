package day28012026;

public class RotateImage {

  // transpose + reverse horizontally
  // public void rotate(int[][] matrix) {
  // int n = matrix.length;
  // var temp = 0;

  // // transpose
  // for (int i = 0; i < n; i++) {
  // for (int j = i; j < n; j++) {
  // if (i != j) {
  // temp = matrix[i][j];
  // matrix[i][j] = matrix[j][i];
  // matrix[j][i] = temp;
  // }
  // }
  // }

  // // reverse row
  // for (int i = 0; i < n; i++) {
  // for (int j = 0; j < n / 2; j++) {
  // temp = matrix[i][j];
  // matrix[i][j] = matrix[i][n - 1 - j];
  // matrix[i][n - 1 - j] = temp;
  // }
  // }
  // }

  // tranpose + reverse horizontal
  public void rotate(int[][] matrix) {
    int n = matrix.length;

    // transponse : (i,j) -> (j,i)
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    // reverse horizontal (i,j) -> (j, n-1-j)
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n - 1 - j];
        matrix[i][n - 1 - j] = temp;
      }
    }
  }

  // reverse vertically + transpose
  public void rotate2(int[][] matrix) {
    int n = matrix.length;

    // reverse vertically
    for (int i = 0; i < n / 2; i++) {
      var temp = matrix[i];
      matrix[i] = matrix[n - 1 - i];
      matrix[n - 1 - i] = temp;
    }

    // transpose
    for (int idx = 0; idx < matrix.length; idx++) {
      for (int j = idx + 1; j < matrix.length; j++) {
        var temp = matrix[idx][j];
        matrix[idx][j] = matrix[j][idx];
        matrix[j][idx] = temp;
      }
    }
  }

  // rotate layer by layer ( move each cell on each layer sequentially)
  public void rotate3(int[][] matrix) {
    int n = matrix.length;
    for (int layer = 0; layer < n / 2; layer++) {
      int first = layer;
      int last = n - 1 - first;

      for (int i = first; i < last; i++) {
        int top = matrix[first][i]; // keep the first position swap
        int offset = i - first;

        matrix[first][i] = matrix[last - offset][first]; // left -> top
        matrix[last - offset][first] = matrix[last][last - offset]; // bottom -> left
        matrix[last][last - offset] = matrix[i][last]; // right -> bottom
        matrix[i][last] = top;
      }
    }
  }

  public static void main(String[] args) {
    RotateImage sol = new RotateImage();
    // int[][] matrix = {
    // { 1, 2, 3 },
    // { 4, 5, 6 },
    // { 7, 8, 9 }
    // };
    // sol.rotate(matrix);
    // for (int[] matrix1 : matrix) {
    // for (int j = 0; j < matrix[0].length; j++) {
    // System.out.print(matrix1[j] + " ");
    // }
    // System.out.println();
    // }

    // int[][] matrix2 = {
    // { 5, 1, 9, 11 },
    // { 2, 4, 8, 10 },
    // { 13, 3, 6, 7 },
    // { 15, 14, 12, 16 }
    // };
    // sol.rotate(matrix2);
    // for (int[] ele : matrix2) {
    // for (int j = 0; j < matrix2[0].length; j++) {
    // System.out.print(ele[j] + " ");
    // }
    // System.out.println();
    // }

    int[][] matrix3 = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 9 }
    };
    sol.rotate(matrix3);
    for (int[] matrix31 : matrix3) {
      for (int j = 0; j < matrix3[0].length; j++) {
        System.out.print(matrix31[j] + " ");
      }
      System.out.println();
    }
  }
}
