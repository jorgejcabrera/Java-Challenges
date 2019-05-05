package com.javachallenges.Java.Challenge;

public class ArraysDs {

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
}
