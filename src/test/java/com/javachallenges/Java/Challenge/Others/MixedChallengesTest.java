package com.javachallenges.Java.Challenge.Others;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MixedChallengesTest {

  @Test
  public void getDayFromSpecificDateShouldWorkOk() {
    // given
    int day = 6;
    int month = 12;
    int year = 2019;

    // then
    assertThat(MixedChallenges.findDay(month, day, year), equalTo("FRIDAY"));
  }

  @Test
  public void currencyFormatByCountryShouldWorkOk() {
    // given
    double price = 12324.134;

    // then
    assertThat(MixedChallenges.currencyFormatByCountry(price, "en", "US"), equalTo("$12,324.13"));
    assertThat(MixedChallenges.currencyFormatByCountry(price, "en", "IN"), equalTo("Rs.12,324.13"));
    assertThat(MixedChallenges.currencyFormatByCountry(price, "zh", "CN"), equalTo("￥12,324.13"));
    assertThat(
        MixedChallenges.currencyFormatByCountry(price, "fr", "FR").equals("12 324,13 €"),
        equalTo(true));
  }

  @Test
  public void processStringsShouldWorkOk() {
    // given
    String A = "hello";
    String B = "world";

    // then
    MixedChallenges.processStrings(A, B);
  }

  @Test
  public void printSubString() {
    // given
    String str = "Helloworld";
    int start = 3;
    int end = 7;

    // then
    MixedChallenges.printSubstring(str, start, end);
  }

  @Test
  public void getSmallestAndLargestShouldWorkOk() {
    // given
    String input = "welcometojava";
    int k = 3;
    // when
    String smallestAndLargest = MixedChallenges.getSmallestAndLargest(input, k);
    // then
    assertThat(smallestAndLargest, equalTo("ava" + "\n" + "wel"));
  }

  @Test
  public void getSmallestAndLargestReceiveInvalidLengthShouldWorkOk() {
    // given
    String input = "welcometojava";
    int k = 20;
    // when
    String smallestAndLargest = MixedChallenges.getSmallestAndLargest(input, k);
    // then
    assertThat(smallestAndLargest, equalTo("" + "\n" + ""));
  }

  @Test
  public void getSmallestAndLargestReceiveALittleLengthShouldWorkOk() {
    // given
    String input = "welcometojava";
    int k = 1;
    // when
    String smallestAndLargest = MixedChallenges.getSmallestAndLargest(input, k);
    // then
    assertThat(smallestAndLargest, equalTo("a" + "\n" + "w"));
  }

  @Test
  public void getSmallestAndLargestReceiveZeroLengthShouldWorkOk() {
    // given
    String input = "welcometojava";
    int k = 0;
    // when
    String smallestAndLargest = MixedChallenges.getSmallestAndLargest(input, k);
    // then
    assertThat(smallestAndLargest, equalTo("" + "\n" + ""));
  }

  @Test
  public void checkRegexSintaxShouldWorkOk() {
    // given
    String pattern = "[AZ[a-z](a-z)";

    // then
    MixedChallenges.checkRegexSintax(pattern);
  }
}