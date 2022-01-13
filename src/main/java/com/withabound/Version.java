package com.withabound;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Returns the value stored in src/main/resources/VERSION */
final class Version {
  private static String SDK_VERSION;

  public static String getSDKVersion() {
    if (SDK_VERSION == null) {
      SDK_VERSION = loadFromFile();
    }
    return SDK_VERSION;
  }

  /** @return value from src/main/resources/VERSION */
  private static String loadFromFile() {
    // https://www.baeldung.com/java-try-with-resources
    try (final InputStream is = Version.class.getClassLoader().getResourceAsStream("VERSION");
        final BufferedReader br =
            new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
      return br.readLine();
    } catch (final Exception e) {
      return "unknown";
    }
  }
}
