package com.withabound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AboundConfigTest {
  @Test
  public void testAboundConfigValidatesAppId() {
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AboundConfig(
                    null, "appSecret", AboundEnvironment.SANDBOX, AboundApiVersion.V2));

    assertEquals("appId cannot be empty!", thrown.getMessage());

    thrown =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AboundConfig("", "appSecret", AboundEnvironment.SANDBOX, AboundApiVersion.V2));

    assertEquals("appId cannot be empty!", thrown.getMessage());
  }

  @Test
  public void testAboundConfigValidatesAppSecret() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new AboundConfig("appId", null, AboundEnvironment.SANDBOX, AboundApiVersion.V2));

    assertThrows(
        IllegalArgumentException.class,
        () -> new AboundConfig("appId", "", AboundEnvironment.SANDBOX, AboundApiVersion.V2));
  }

  @Test
  public void testAboundConfigValidatesEnvironment() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new AboundConfig("appId", "appSecret", null, AboundApiVersion.V2));
  }

  @Test
  public void testAboundConfigValidatesApiVersion() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new AboundConfig("appId", "appSecret", AboundEnvironment.SANDBOX, null));
  }

  @Test
  public void testValidAboundConfig() {
    final AboundConfig validConfig =
        new AboundConfig("appId", "appSecret", AboundEnvironment.SANDBOX, AboundApiVersion.V2);

    assertEquals("appId", validConfig.getAppId());
    assertEquals("appSecret", validConfig.getAppSecret());
    assertEquals(AboundEnvironment.SANDBOX, validConfig.getEnvironment());
    assertEquals(AboundApiVersion.V2, validConfig.getApiVersion());
  }
}
