public class SearchMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0;
    int right = m * n - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int currElement = matrix[mid / n][mid % n];
      if (currElement == target) { // /n is row, %n = col
        return true;
      } else if (currElement < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    SearchMatrix solution = new SearchMatrix();
    int[][] matrix = {
        { 1, 3, 5, 7 },
        { 10, 11, 16, 20 },
        { 23, 30, 34, 60 }
    };
    int target = 3;
    boolean result = solution.searchMatrix(matrix, target);
    System.out.println("Is target " + target + " in the matrix? " + result); // Output: true
  }

}
