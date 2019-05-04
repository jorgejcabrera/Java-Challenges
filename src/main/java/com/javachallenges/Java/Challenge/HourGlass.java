package com.javachallenges.Java.Challenge;

import java.util.ArrayList;
import java.util.List;

public class HourGlass {

  public static List<int[][]> getVertexesList(int[][] matrix) {
    List<int[][]> vertexes = new ArrayList<>();
    if (matrix.length != matrix[0].length) return vertexes;

    if (!(matrix.length > 3)) return vertexes;

    int width = matrix[0].length;
    int high = matrix.length;
    int i;
    int j;
    for (i = 0; i < width; i++) {
      if (i + 2 >= width) continue;
      for (j = 0; j < high; j++) {
        if (j + 2 >= width) continue;
        int[][] vertex = new int[][] {{i, j}};
        vertexes.add(vertex);
      }
    }
    return vertexes;
  }

  public static Integer sum(int[][] matrix, int[][] vertex) {
    int x = vertex[0][0];
    int y = vertex[0][1];
    int firstRowSum = matrix[x][y] + matrix[x][y + 1] + matrix[x][y + 2];
    int secondRowSum = matrix[x + 1][y + 1];
    int thirdRowSum = matrix[x + 2][y] + matrix[x + 2][y + 1] + matrix[x + 2][y + 2];
    return firstRowSum + secondRowSum + thirdRowSum;
  }

  public static int hourglassSum(int[][] matrix) {
    List<int[][]> vertexes = getVertexesList(matrix);
    Integer maxSum = 0;
    for (int[][] vertex : vertexes) {
      Integer currentSum = sum(matrix, vertex);
      if (currentSum > maxSum) maxSum = currentSum;
    }
    return maxSum;
  }
}
