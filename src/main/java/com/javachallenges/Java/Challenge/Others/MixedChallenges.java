package com.javachallenges.Java.Challenge.Others;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Locale;

public class MixedChallenges {

  /**
   * @param month
   * @param day
   * @param year
   * @return retrieve the day of week from specific date
   */
  public static String findDay(int month, int day, int year) {
    String dayOfWeek = null;
    YearMonth yearMonth = YearMonth.of(year, month);
    if (!(day > yearMonth.lengthOfMonth() || month > 12))
      dayOfWeek = LocalDate.of(year, month, day).getDayOfWeek().toString();
    return dayOfWeek;
  }

  /**
   * @param price
   * @param language
   * @param country
   * @return retrieve an amount of money according to given country code
   */
  public static String currencyFormatByCountry(double price, String language, String country) {
    Locale locale = new Locale(language, country);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    return currencyFormatter.format(price);
  }
}
