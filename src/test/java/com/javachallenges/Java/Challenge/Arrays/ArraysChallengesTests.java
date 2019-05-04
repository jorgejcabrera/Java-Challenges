package com.javachallenges.Java.Challenge.Arrays;

import com.javachallenges.Java.Challenge.HourGlass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ArraysChallengesTests {

  @Test
  public void challengeArrayOne_findElementInUnsortedIntegerListShouldWorkOk() {
    // given
    List<Integer> arr =
        new ArrayList<Integer>() {
          {
            add(1);
            add(2);
            add(128458);
          }
        };
    IntStream.rangeClosed(1, 1500)
        .forEach(i -> arr.add(new Random().ints(1).findFirst().getAsInt()));
    int size = arr.size();
    Integer[] sortedArr = arr.toArray(new Integer[size]);

    // when
    Arrays.parallelSort(sortedArr);

    // then
    assertTrue(Arrays.binarySearch(sortedArr, 1) >= 0);
    assertTrue(Arrays.binarySearch(sortedArr, 2) >= 0);
    assertTrue(Arrays.binarySearch(sortedArr, 128458) >= 0);
  }

  @Test
  public void challengeArrayTwo_hourglassSumIn2DArray() {
    // given
    int[][] matrix = new int[6][6];
    matrix[0][0] = -9;
    matrix[0][1] = -9;
    matrix[0][2] = -9;
    matrix[0][3] = 1;
    matrix[0][4] = 1;
    matrix[0][5] = 1;

    matrix[1][1] = -9;
    matrix[1][3] = 4;
    matrix[1][4] = 3;
    matrix[1][5] = 2;

    matrix[2][0] = -9;
    matrix[2][1] = -9;
    matrix[2][2] = -9;
    matrix[2][3] = 1;
    matrix[2][4] = 2;
    matrix[2][5] = 3;

    matrix[3][2] = 8;
    matrix[3][3] = 6;
    matrix[3][4] = 6;

    matrix[4][3] = -2;

    matrix[5][2] = 1;
    matrix[5][3] = 2;
    matrix[5][4] = 4;

    // when
    List<int[][]> vertexes = HourGlass.getVertexesList(matrix);
    Integer firstVertexSum = HourGlass.sum(matrix, vertexes.get(0));
    Integer secondVertexSum = HourGlass.sum(matrix, vertexes.get(1));
    Integer maxSum = HourGlass.hourglassSum(matrix);

    // then
    assertThat(vertexes.size(), equalTo(16));
    assertThat(firstVertexSum, equalTo(-63));
    assertThat(secondVertexSum, equalTo(-34));
    assertThat(maxSum, equalTo(28));
  }
}
