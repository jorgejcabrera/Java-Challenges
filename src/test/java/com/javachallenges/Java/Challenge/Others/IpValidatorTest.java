package com.javachallenges.Java.Challenge.Others;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IpValidatorTest {

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
    assertThat(invalidIp1.matches(IPValidator.REGEX), equalTo(false));
    assertThat(invalidIp2.matches(IPValidator.REGEX), equalTo(false));
    assertThat(invalidIp3.matches(IPValidator.REGEX), equalTo(false));
    assertThat(invalidIp4.matches(IPValidator.REGEX), equalTo(false));
    assertThat(invalidIp5.matches(IPValidator.REGEX), equalTo(false));
    assertThat(validIp1.matches(IPValidator.REGEX), equalTo(true));
    assertThat(validIp2.matches(IPValidator.REGEX), equalTo(true));
    assertThat(validIp3.matches(IPValidator.REGEX), equalTo(true));
  }
}
