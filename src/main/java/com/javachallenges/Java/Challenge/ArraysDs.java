package com.javachallenges.Java.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysDs {

  private static Integer queryTypeIndex = 0;
  private static Integer xIndex = 1;
  private static Integer yIndex = 2;

  public static int[] reverse(int[] a) {
    int size = a.length;
    int elements = size - 1;
    int[] reverse = new int[size];
    int i;
    for (i = 0; i < size; i++) {
      reverse[i] = a[elements - i];
    }
    return reverse;
  }

  public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    int lastAnswer = 0;
    int index;
    Integer queryType;
    Integer x;
    Integer y;
    List<List<Integer>> sequences = createSequences(n);
    List<Integer> output = new ArrayList<>();

    for (List<Integer> query : queries) {
      queryType = query.get(queryTypeIndex);
      x = query.get(xIndex);
      y = query.get(yIndex);
      index = calculateIndex(x, lastAnswer, n);
      if (queryType == 1) {
        sequences.get(index).add(y);
      } else if (queryType == 2) {
        int sequenceSize = sequences.get(index).size();
        lastAnswer = sequences.get(index).get(y % sequenceSize);
        output.add(lastAnswer);
        System.out.println(lastAnswer);
      }
    }
    return output/*sequences.stream().flatMap(List::stream).collect(Collectors.toList())*/;
  }

  private static List<List<Integer>> createSequences(int n) {
    List<List<Integer>> sequences = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      sequences.add(new ArrayList<>());
    }
    return sequences;
  }

  private static int calculateIndex(int value, int lastAnswer, int n) {
    return (value ^ lastAnswer) % n;
  }
}
