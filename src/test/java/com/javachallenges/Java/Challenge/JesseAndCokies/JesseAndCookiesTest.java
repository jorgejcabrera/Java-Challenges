package com.javachallenges.Java.Challenge.JesseAndCokies;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.javachallenges.Java.Challenge.JesseAndCookies.JesseAndCookies;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JesseAndCookiesTest {

  @Test
  public void cookiesMethodShouldWorkOk() {
    // given
    int[] A = {3, 12, 1, 9, 10, 2};
    int k = 7;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(2));
  }

  @Test
  public void caseFiveCookiesMethodShouldWorkOk() {
    // given
    int[] A = {1, 1, 1};
    int k = 10;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(-1));
  }

  @Test
  public void caseOneCookiesMethodShouldWorkOk() {
    // given
    int[] A = {1, 2, 3};
    int k = 13;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(2));
  }

  @Test
  public void cookiesMethodShouldReturnNegativeValueWhenItIsNoPossible() {
    // given
    int[] A = {1, 2, 3};
    int k = 20;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(-1));
  }

  @Test
  public void cookiesMethodShouldReturnZeroWhenAllValueAreBiggerThanK() {
    // given
    int[] A = {12, 9, 10, 20};
    int k = 7;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(0));
  }

  @Test
  public void caseTwoCookiesMethodShouldWorkOk() throws IOException {
    // given
    String filePath = "json/JesseAndCookies.json";
    File file = ResourceUtils.getFile("classpath:" + filePath);
    int[] A =
        new GsonBuilder()
            .create()
            .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), int[].class);
    int k = 1059589;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(17595));
  }

  @Test
  public void caseThreeCookiesMethodShouldWorkOk() throws IOException {
    // given
    String filePath = "json/JesseAndCookiesOne.json";
    File file = ResourceUtils.getFile("classpath:" + filePath);
    int[] A =
            new GsonBuilder()
                    .create()
                    .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), int[].class);
    int k = 291140174;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(67606));
  }

  @Test
  public void caseFourCookiesMethodShouldWorkOk() throws IOException {
    // given
    String filePath = "json/JesseAndCookiesTwo.json";
    File file = ResourceUtils.getFile("classpath:" + filePath);
    int[] A =
            new GsonBuilder()
                    .create()
                    .fromJson(new JsonParser().parse(new FileReader(file)).getAsJsonArray(), int[].class);
    int k = 578646143;

    // then
    assertThat(JesseAndCookies.cookies(k, A), equalTo(99998));
  }
}
