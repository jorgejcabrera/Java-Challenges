package com.javachallenges.Java.Challenge.Arrays;

import com.javachallenges.Java.Challenge.Others.MixedChallenges;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
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

  @Test
  public void challengeArrayTwo_withCompleteMatrixShouldWorkOk() {
    // given
    int[][] matrix = new int[6][6];
    matrix[0][0] = -1;
    matrix[0][1] = -1;
    matrix[0][2] = 0;
    matrix[0][3] = -9;
    matrix[0][4] = -2;
    matrix[0][5] = -2;

    matrix[1][0] = -2;
    matrix[1][1] = -1;
    matrix[1][2] = -6;
    matrix[1][3] = -8;
    matrix[1][4] = -2;
    matrix[1][5] = -5;

    matrix[2][0] = -1;
    matrix[2][1] = -1;
    matrix[2][2] = -1;
    matrix[2][3] = -2;
    matrix[2][4] = -3;
    matrix[2][5] = -4;

    matrix[3][0] = -1;
    matrix[3][1] = -9;
    matrix[3][2] = -2;
    matrix[3][3] = -4;
    matrix[3][4] = -4;
    matrix[3][5] = -5;

    matrix[4][0] = -7;
    matrix[4][1] = -3;
    matrix[4][2] = -3;
    matrix[4][3] = -2;
    matrix[4][4] = -9;
    matrix[4][5] = -9;

    matrix[5][0] = -1;
    matrix[5][1] = -3;
    matrix[5][2] = -1;
    matrix[5][3] = -2;
    matrix[5][4] = -4;
    matrix[5][5] = -5;

    // when
    List<int[][]> vertexes = HourGlass.getVertexesList(matrix);
    Integer firstVertexSum = HourGlass.sum(matrix, vertexes.get(0));
    Integer secondVertexSum = HourGlass.sum(matrix, vertexes.get(1));
    Integer maxSum = HourGlass.hourglassSum(matrix);

    // then
    assertThat(vertexes.size(), equalTo(16));
    assertThat(firstVertexSum, equalTo(-6));
    assertThat(secondVertexSum, equalTo(-20));
    assertThat(maxSum, equalTo(-6));
  }

  @Test
  public void challengeArrayThreeReverseArrayShouldWorkOk() {
    // given
    int[] a = new int[] {1, 4, 3, 2};

    // when
    int[] reversedArray = ArraysDs.reverse(a);

    // then
    assertThat(reversedArray[0], equalTo(2));
    assertThat(reversedArray[1], equalTo(3));
    assertThat(reversedArray[2], equalTo(4));
    assertThat(reversedArray[3], equalTo(1));
  }

  @Test
  public void challengeArrayFourDynamicArrayShouldWorkOk() {
    // given
    int numberOfSequences = 2;
    List<Integer> firstQuery = Arrays.asList(1, 0, 5);
    List<Integer> secondQuery = Arrays.asList(1, 1, 7);
    List<Integer> thirdQuery = Arrays.asList(1, 0, 3);
    List<Integer> fourthQuery = Arrays.asList(2, 1, 0);
    List<Integer> fifthQuery = Arrays.asList(2, 1, 1);
    List<List<Integer>> queries =
        Arrays.asList(firstQuery, secondQuery, thirdQuery, fourthQuery, fifthQuery);

    // when
    List<Integer> dynamicArray = ArraysDs.dynamicArray(numberOfSequences, queries);

    // then
    assertThat(dynamicArray.contains(7), is(true));
    assertThat(dynamicArray.contains(3), is(true));
  }

  @Test
  public void challengeFiveReverseArrayShouldWorkOk() {
    // given
    int[] array = {1, 2, 3, 4, 5};
    int d = 4;

    // when
    int[] result = ArraysDs.leftRotation(array, d);

    // then
    assertThat(result[0], equalTo(5));
    assertThat(result[1], equalTo(1));
    assertThat(result[4], equalTo(4));
  }

  @Test
  public void challengeMatchingStrings() {
    // given
    String[] strings = {"aba", "baba", " aba", "xzxb"};
    String[] queries = {"aba", "xzxb", "ab"};

    // when
    int[] results = ArraysDs.matchingStrings(strings, queries);

    // then
    assertThat(results[0], equalTo(2));
  }
}
