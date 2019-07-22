package com.javachallenges.Java.Challenge.Santander;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

public class SantanderChallenge {

  private static final String ERROR = "ERROR";
  // ex 1
  public static int findMax(int[] myArray) {
    if (myArray.length == 0) return MIN_VALUE;
    Arrays.sort(myArray);
    return myArray[myArray.length - 1];
  }

  // ex 2
  public static boolean isReversible(String[] word) {
    if (word == null) return false;
    String[] original = Arrays.copyOf(word, word.length);
    reverseArray(word, 0, word.length - 1);
    return checkEquality(original, word);
  }

  private static void reverseArray(String[] arr, int start, int end) {
    String temp;
    while (start < end) {
      temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }

  private static boolean checkEquality(String[] s1, String[] s2) {
    if (s1 == s2) return true;

    if (s1 == null || s2 == null) return false;

    int n = s1.length;
    if (n != s2.length) return false;

    for (int i = 0; i < n; i++) {
      if (!s1[i].equals(s2[i])) return false;
    }

    return true;
  }

  // ej 3
  public static void createXBySize(int n) {
    if (n == 0) System.out.println(ERROR);
    for (int i = 0; i < n; i++) {
      int start = i;
      int end = (n - 1) - i;
      for (int j = 0; j < n; j++) {
        if (j == start || j == end) {
          System.out.print("X");
        } else {
          System.out.print("_");
        }
      }
      System.out.print("\n");
    }
  }

  // ej 4
  public static void createHistogram(int[] myArray) {
    int[] distinct = Arrays.stream(myArray).sorted().distinct().toArray();
    distinct = completeArray(distinct);
    for (int i = 0; i < distinct.length; i++) {
      int value = distinct[i];
      long occurrence = Arrays.stream(myArray).filter(j -> j == value).count();
      printHistogram(value, occurrence);
    }
  }

  private static int[] completeArray(int[] myArray) {
    int length = myArray.length;
    int maxValue = myArray[length - 1];
    List<Integer> myList = Arrays.stream(myArray).boxed().collect(Collectors.toList());
    for (int i = 1; i < maxValue; i++) {
      if (!myList.contains(i)) {
        myArray = addElement(myArray, i);
      }
    }
    return Arrays.stream(myArray).sorted().toArray();
  }

  private static void printHistogram(int value, long occurrence) {
    System.out.format("%d: ", value);
    for (int i = 0; i < occurrence; i++) {
      System.out.print("*");
    }
    System.out.print("\n");
  }

  static int[] addElement(int[] a, int e) {
    a = Arrays.copyOf(a, a.length + 1);
    a[a.length - 1] = e;
    return a;
  }
}
