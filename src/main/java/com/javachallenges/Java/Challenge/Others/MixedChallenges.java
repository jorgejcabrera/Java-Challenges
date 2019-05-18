package com.javachallenges.Java.Challenge.Others;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.util.stream.Collectors.toMap;

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

  /**
   * @param word
   * @return true when input is a palindrome word
   */
  public static boolean isPalindrome(String word) {
    if (word == null) return false;
    String reverse = reverse(word);
    return word.equals(reverse);
  }

  private static String reverse(String original) {
    String reversed = "";
    for (int i = original.length() - 1; 0 <= i; i--) {
      reversed += original.charAt(i);
    }
    return reversed;
  }

  public static void processStrings(String A, String B) {
    System.out.println(String.join("", A, B).length() - 1);
    String isLexicographicallyGreater = A.compareTo(B) > 0 ? "Yes" : "No";
    System.out.println(isLexicographicallyGreater);
    System.out.println(String.format("%s %s", capitalize(A), capitalize(B)));
  }

  public static void printSubstring(String str, int start, int end) {
    if (str == null || str.isEmpty() || end < start) return;
    System.out.println(str.substring(start, end));
  }

  /**
   * @param s
   * @param k
   * @return retrieve a string which contains the largest ans smallest lexicographical substrings
   */
  public static String getSmallestAndLargest(String s, int k) {
    if (k == 0 || k > s.length()) return "" + "\n" + "";
    String smallest;
    String largest;
    java.util.List<String> substrings = new java.util.ArrayList<>();
    for (int i = 0; i <= s.length() - k; i++) {
      substrings.add(s.substring(i, i + k));
    }
    substrings = substrings.stream().sorted().collect(java.util.stream.Collectors.toList());
    smallest = substrings.isEmpty() ? "" : substrings.get(0);
    largest = substrings.isEmpty() ? "" : substrings.get(substrings.size() - 1);

    return smallest + "\n" + largest;
  }

  public static void checkRegexSyntax(String pattern) {
    try {
      Pattern.compile(pattern);
      System.out.println("Valid");
    } catch (PatternSyntaxException exception) {
      System.out.println("Invalid");
    }
  }

  /**
   * @param a
   * @param b
   * @return true if a and b are anagrams, if they contains the same characters in the same
   *     frequency
   */
  static boolean isAnagram(String a, String b) {
    if (a == null || b == null || a.isEmpty() || b.isEmpty() || a.length() != b.length())
      return false;
    Map<Character, Integer> freqA =
        a.toLowerCase()
            .chars()
            .boxed()
            .collect(toMap(k -> (char) k.intValue(), v -> 1, Integer::sum));

    Map<Character, Integer> freqB =
        b.toLowerCase()
            .chars()
            .boxed()
            .collect(java.util.stream.Collectors.toMap(k -> (char) k.intValue(), v -> 1, Integer::sum));
    return freqA.equals(freqB);
  }

  private static String capitalize(String input) {
    return input.substring(0, 1).toUpperCase() + input.substring(1);
  }
}
