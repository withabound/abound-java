package com.withabound.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class TestUtils {
  public static final String TEST_APP_ID = "appId_test48e7eaa3175a66354e00626542d2";
  public static final String TEST_APP_SECRET = "appSecret_testf54672359db6693429e1d3e14e2c";

  public static final String TEST_USER_ID = "userId_test24b05d761ff58b5931bd07778c67b4e818e4";

  public static final String TEST_ACCESS_TOKEN = "accessToken_testeyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiJhcHBJZF90ZXN0NDhlN2VhYTMxNzVhNjYzNTRlMDA2MjY1NDJkMiIsImNyZWF0ZWRfdGltZXN0YW1wIjoxNjU1MDk2NDAwMDAwLCJlbnZpcm9ubWVudCI6Imh0dHBzOi8vc2FuZGJveC1hcGkud2l0aGFib3VuZC5jb20vdjIiLCJleHBpcmF0aW9uX3RpbWVzdGFtcCI6MzI1MDM3MDE2MDAwMDAsInN0YXR1cyI6IkFjdGl2ZSIsInVzZXJfaWQiOiJ1c2VySWRfdGVzdDI0YjA1ZDc2MWZmNThiNTkzMWJkMDc3NzhjNjdiNGU4MThlNCIsImlhdCI6MTY1NTEzMDMxM30.dOUIyxTRV0QDmrFiy-GoyhKc8qru3pymIcPS5cGTaNk";

  private static final Random RND = new Random();
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
  private static final String NUMERIC = "0123456789";

  public static String randomNumberString() {
    return randomNumberString(13);
  }

  /** returns a string containing only numbers */
  public static String randomNumberString(final int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++) {
      result.append(randomNumber());
    }

    return result.toString();
  }

  public static Double randomDouble() {
    return randomDouble(100);
  }

  public static Double randomDouble(final int max) {
    return RND.nextDouble() * max;
  }

  public static int randomInt() {
    return randomInt(100);
  }

  public static int randomInt(final int max) {
    return (int) Math.round(randomDouble(max));
  }

  /** @return Double with 2 decimal precision (#.xx) */
  public static Double randomCurrencyAmount() {
    return randomCurrencyAmount(100);
  }

  /** @return Double with 2 decimal precision (#.xx) */
  public static Double randomCurrencyAmount(final int max) {
    final BigDecimal currencyAmount = BigDecimal.valueOf(randomDouble(max));
    return currencyAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public static Character randomNumber() {
    return random(NUMERIC);
  }

  public static Character randomAlphabeticChar() {
    return random(ALPHABET);
  }

  public static Character random(final String characterSequence) {
    return characterSequence.charAt(RND.nextInt(characterSequence.length()));
  }

  public static String randomAlphabetic() {
    return randomAlphabetic(13);
  }

  /** returns a string with only letters */
  public static String randomAlphabetic(final int length) {
    StringBuilder str = new StringBuilder();

    for (int i = 0; i < length; i++) {
      str.append(randomAlphabeticChar());
    }

    return str.toString();
  }

  public static String randomEmail() {
    return randomAlphabetic() + "@example.com";
  }

  /** @return a random date in the past 2 years, formatted as YYYY-MM-DD */
  public static String randomDate() {
    final long minDay =
        LocalDate.of(Calendar.getInstance().get(Calendar.YEAR) - 2, 1, 1).toEpochDay();
    final long maxDay = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), 1, 1).toEpochDay();
    final long randomDay = minDay + RND.nextInt((int) maxDay - (int) minDay);

    final LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

    return randomDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
