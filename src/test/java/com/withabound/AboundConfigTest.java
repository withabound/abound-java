package com.withabound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class AboundConfigTest {
  @Test
  public void testAboundConfigValidatesAppId() {
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AboundConfig(
                    null,
                    UUID.randomUUID().toString(),
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2));

    assertEquals("appId cannot be empty!", thrown.getMessage());

    thrown =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AboundConfig(
                    "",
                    UUID.randomUUID().toString(),
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2));

    assertEquals("appId cannot be empty!", thrown.getMessage());
  }

  @Test
  public void testAboundConfigValidatesAppSecret() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new AboundConfig(
                UUID.randomUUID().toString(),
                null,
                AboundEnvironment.SANDBOX,
                AboundApiVersion.V2));

    assertThrows(
        IllegalArgumentException.class,
        () ->
            new AboundConfig(
                UUID.randomUUID().toString(), "", AboundEnvironment.SANDBOX, AboundApiVersion.V2));
  }

  @Test
  public void testAboundConfigValidatesEnvironment() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new AboundConfig(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                null,
                AboundApiVersion.V2));
  }

  @Test
  public void testAboundConfigValidatesApiVersion() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new AboundConfig(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                AboundEnvironment.SANDBOX,
                null));
  }

  @Test
  public void testValidAboundConfig() {
    final String appId = UUID.randomUUID().toString();
    final String appSecret = UUID.randomUUID().toString();

    final AboundConfig validConfig =
        new AboundConfig(appId, appSecret, AboundEnvironment.SANDBOX, AboundApiVersion.V2);

    assertEquals(appId, validConfig.getAppId());
    assertEquals(appSecret, validConfig.getAppSecret());
    assertEquals(AboundEnvironment.SANDBOX, validConfig.getEnvironment());
    assertEquals(AboundApiVersion.V2, validConfig.getApiVersion());
  }
}
