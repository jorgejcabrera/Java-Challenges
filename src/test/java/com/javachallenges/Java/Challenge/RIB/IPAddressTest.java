package com.javachallenges.Java.Challenge.RIB;

import inet.ipaddr.AddressStringException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IPAddressTest {

  @Test
  public void whenSubnetContainsIPAddressThenShouldWorkOk() throws AddressStringException {
    // given
    String onePrefix = "198.255.238.0/24";
    String twoPrefix = "198.255.0.0/16";
    String threePrefix = "198.168.240.0/20";

    // when
    IPAddress oneIpAddress = new IPAddressString(onePrefix).toAddress();
    IPAddress twoIpAddress = new IPAddressString(twoPrefix).toAddress();
    IPAddress threeIpAddress = new IPAddressString(threePrefix).toAddress();

    // then
    assertThat(
        oneIpAddress.contains(new IPAddressString("198.255.238.254").toAddress()), equalTo(true));
    assertThat(
        oneIpAddress.contains(new IPAddressString("198.255.0.254").toAddress()), equalTo(false));

    assertThat(
        twoIpAddress.contains(new IPAddressString("198.255.238.254").toAddress()), equalTo(true));
    assertThat(
        twoIpAddress.contains(new IPAddressString("198.255.0.254").toAddress()), equalTo(true));
    assertThat(
        twoIpAddress.contains(new IPAddressString("195.255.0.254").toAddress()), equalTo(false));
    assertThat(
        twoIpAddress.contains(new IPAddressString("12.255.0.254").toAddress()), equalTo(false));

    assertThat(
        threeIpAddress.contains(new IPAddressString("198.168.238.0").toAddress()), equalTo(false));
    assertThat(
        threeIpAddress.contains(new IPAddressString("198.168.238.255").toAddress()),
        equalTo(false));
    assertThat(
        threeIpAddress.contains(new IPAddressString("198.168.240.255").toAddress()), equalTo(true));
    assertThat(
        threeIpAddress.contains(new IPAddressString("198.169.240.255").toAddress()),
        equalTo(false));
  }

  @Test
  public void whenSubnetContainsAnotherSubnetThenShouldWorkOk() throws AddressStringException {
    // given
    String onePrefix = "198.255.238.0/24";

    // when
    IPAddress oneIpAddress = new IPAddressString(onePrefix).toAddress();

    // then
    assertThat(
        oneIpAddress.contains(new IPAddressString("198.255.238.0/25").toAddress()), equalTo(true));
    assertThat(
        oneIpAddress.contains(new IPAddressString("198.255.238.0/30").toAddress()), equalTo(true));
    assertThat(
        oneIpAddress.contains(new IPAddressString("198.255.238.128/25").toAddress()),
        equalTo(true));
    assertThat(
        oneIpAddress.contains(new IPAddressString("199.255.238.128/25").toAddress()),
        equalTo(false));
    assertThat(
        oneIpAddress.contains(new IPAddressString("199.255.237.128/25").toAddress()),
        equalTo(false));
  }
}
