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
}
