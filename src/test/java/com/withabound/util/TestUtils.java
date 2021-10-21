package com.withabound.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class TestUtils {
  private static final Random RND = new Random();
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  public static final String TEST_APP_ID = "appId_test48e7eaa3175a66354e00626542d2";
  public static final String TEST_APP_SECRET = "appSecret_testf54672359db6693429e1d3e14e2c";

  public static final String TEST_USER_ID = "userId_test24b05d761ff58b5931bd07778c67b4e818e4";

  public static Double randomDouble() {
    return randomDouble(100);
  }

  public static Double randomDouble(final int max) {
    return RND.nextDouble() * max;
  }

  /** @return double with 2 decimal precision (#.xx) */
  public static Double randomCurrencyAmount() {
    final BigDecimal currencyAmount = BigDecimal.valueOf(randomDouble());
    return currencyAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public static Character randomChar() {
    return ALPHABET.charAt(RND.nextInt(ALPHABET.length()));
  }

  public static String randomString() {
    return randomString(13);
  }

  public static String randomString(final int length) {
    StringBuilder str = new StringBuilder();

    for (int i = 0; i < length; i++) {
      str.append(randomChar());
    }

    return str.toString();
  }

  public static String randomEmail() {
    return randomString() + "@example.com";
  }
}
