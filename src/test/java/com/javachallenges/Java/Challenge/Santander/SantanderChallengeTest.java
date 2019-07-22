package com.javachallenges.Java.Challenge.Santander;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SantanderChallengeTest {

  @Test
  public void caseOne_findMaxTest() {
    int[] myArray = {13, 2, 4, 35, 1};
    System.out.format("%d", SantanderChallenge.findMax(myArray));
    assertThat(SantanderChallenge.findMax(myArray), equalTo(35));
  }

  @Test
  public void caseTwo_findMaxTest() {
    int[] myArray = {};
    assertThat(SantanderChallenge.findMax(myArray), equalTo(Integer.MIN_VALUE));
  }

  @Test
  public void checkIfAnArrayStringIsReversible() {
    String[] myArray = {"a", "b", "c", "d", "d", "c", "b", "a"};
    String[] otherArray = {"a", "b", "c", "d", "d", "b", "c", "a"};
    assertThat(SantanderChallenge.isReversible(myArray), equalTo(true));
    assertThat(SantanderChallenge.isReversible(otherArray), equalTo(false));
  }

  @Test
  public void createXByIndexTest() {
    SantanderChallenge.createXBySize(5);
    SantanderChallenge.createXBySize(6);
  }

  @Test
  public void createHistogram() {
    int[] myArray = {1, 2, 1, 3, 3, 1, 2, 1, 5, 1};

    SantanderChallenge.createHistogram(myArray);
  }
}
