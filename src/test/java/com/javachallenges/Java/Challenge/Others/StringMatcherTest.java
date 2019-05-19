package com.javachallenges.Java.Challenge.Others;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringMatcherTest {

  @Test
  public void ipValidatorShouldWorkOk() {
    // given
    String invalidIp1 = "000.12.234.23.23";
    String invalidIp2 = "666.666.23.23";
    String invalidIp3 = ".213.123.23.32";
    String invalidIp4 = "23.45.22.32.";
    String invalidIp5 = "I.Am.not.an.ip";
    String validIp1 = "000.12.12.034";
    String validIp2 = "121.234.12.12";
    String validIp3 = "23.45.12.56";

    // then
    assertThat(invalidIp1.matches(StringMatcher.IP_REGEX), equalTo(false));
    assertThat(invalidIp2.matches(StringMatcher.IP_REGEX), equalTo(false));
    assertThat(invalidIp3.matches(StringMatcher.IP_REGEX), equalTo(false));
    assertThat(invalidIp4.matches(StringMatcher.IP_REGEX), equalTo(false));
    assertThat(invalidIp5.matches(StringMatcher.IP_REGEX), equalTo(false));
    assertThat(validIp1.matches(StringMatcher.IP_REGEX), equalTo(true));
    assertThat(validIp2.matches(StringMatcher.IP_REGEX), equalTo(true));
    assertThat(validIp3.matches(StringMatcher.IP_REGEX), equalTo(true));
  }

  @Test
  public void testUsernameValidatorTestShouldWorkOk() {
    // given
    String username1 = "Julia";
    String username2 = "Samantha";
    String username3 = "Samantha_21";
    String username4 = "1Samantha";
    String username5 = "Samantha?10_2A";
    String username6 = "JuliaZ007";
    String username7 = "Julia@007";
    String username8 = "_Julia007";

    // then
    assertThat(username1.matches(StringMatcher.USERNAME_REGEX), equalTo(false));
    assertThat(username2.matches(StringMatcher.USERNAME_REGEX), equalTo(true));
    assertThat(username3.matches(StringMatcher.USERNAME_REGEX), equalTo(true));
    assertThat(username4.matches(StringMatcher.USERNAME_REGEX), equalTo(false));
    assertThat(username5.matches(StringMatcher.USERNAME_REGEX), equalTo(false));
    assertThat(username6.matches(StringMatcher.USERNAME_REGEX), equalTo(true));
    assertThat(username7.matches(StringMatcher.USERNAME_REGEX), equalTo(false));
    assertThat(username8.matches(StringMatcher.USERNAME_REGEX), equalTo(false));
  }
}
