package com.javachallenges.Java.Challenge.JesseAndCookies;

import java.util.Arrays;

public class JesseAndCookies {

  private static int LEAST_SWEET_COOKIE = 0;
  private static int SECOND_LEAST_SWEET_COOKIE = 1;

  public static int cookies(int k, int[] A) {
    Arrays.sort(A);
    int maxValue = A[A.length - 1];
    long max = (Long.valueOf(maxValue) * Long.valueOf(A.length) * 2 - maxValue);
    int frequency =
        Arrays.stream(A)
            .filter(value -> value == maxValue || (maxValue - value) < 0.08 * maxValue)
            .toArray()
            .length;
    if (frequency > 0) max = frequency * A.length + max;
    if (k >= max) return -1;
    int count = 0;
    while (A[LEAST_SWEET_COOKIE] < k) {
      A[SECOND_LEAST_SWEET_COOKIE] = A[LEAST_SWEET_COOKIE] + 2 * A[SECOND_LEAST_SWEET_COOKIE];
      A[LEAST_SWEET_COOKIE] = 0;
      LEAST_SWEET_COOKIE++;
      SECOND_LEAST_SWEET_COOKIE++;
      Arrays.sort(A);
      count++;
      if (A.length - count == 1 && A[count] < k) return -1;
    }
    return count;
  }
}
