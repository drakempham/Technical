package day25022026;

import java.util.LinkedList;
import java.util.Queue;

public class IslandParameter {
  // brute force
  // time: O(N2)
  // space: O(1)
  public int islandPerimeter(int[][] grid) {
    int perimeter = 0;
    int rows = grid.length;
    int cols = grid[0].length;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          // Check all 4 sides
          if (i == 0 || grid[i - 1][j] == 0)
            perimeter++; // Top
          if (i == rows - 1 || grid[i + 1][j] == 0)
            perimeter++; // Bottom
          if (j == 0 || grid[i][j - 1] == 0)
            perimeter++; // Left
          if (j == cols - 1 || grid[i][j + 1] == 0)
            perimeter++; // Right
        }
      }
    }
    return perimeter;
  }

  // BFS
  // time:O(n2) -> but optimize more than BF if n is large
  // space: O(1) or O(n) is use [][] visisted
  // mark the elements added to queue = true as soon as you add it
  private int[][] dirs = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

  public int islandParameter2(int[][] grid) {
    Queue<int[]> cells = new LinkedList<>();
    int perimeter = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          cells.offer(new int[] { i, j });
          grid[i][j] = 2;

          while (!cells.isEmpty()) {
            int[] cell = cells.poll();
            for (int[] dir : dirs) {
              int nextR = cell[0] + dir[0];
              int nextC = cell[1] + dir[1];
              if (nextR < 0 || nextR == grid.length || nextC < 0 || nextC == grid[0].length
                  || grid[nextR][nextC] == 0) {
                perimeter++;
              } else if (grid[nextR][nextC] == 1) {
                cells.offer(new int[] { nextR, nextC });
                grid[nextR][nextC] = 2;
              }
            }
          }

          break;
        }
      }
    }

    return perimeter;
  }

  public static void main(String[] args) {
    IslandParameter sol = new IslandParameter();
    // System.out
    // .println(sol.islandParameter2(new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, {
    // 0, 1, 0, 0 }, { 1, 1, 0, 0 } }));
    System.out.println(sol.islandParameter2(new int[][] { { 1, 1 }, { 1, 1 } }));
  }
}
