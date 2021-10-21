package com.withabound;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class AboundConfigTest {
  @Test
  public void testAboundConfigValidatesAppId() {
    assertThatThrownBy(
            () ->
                new AboundConfig(
                    null,
                    UUID.randomUUID().toString(),
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("appId cannot be empty!");

    assertThatThrownBy(
            () ->
                new AboundConfig(
                    "",
                    UUID.randomUUID().toString(),
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("appId cannot be empty!");
  }

  @Test
  public void testAboundConfigValidatesAppSecret() {
    assertThatThrownBy(
            () ->
                new AboundConfig(
                    UUID.randomUUID().toString(),
                    null,
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(
            () ->
                new AboundConfig(
                    UUID.randomUUID().toString(),
                    "",
                    AboundEnvironment.SANDBOX,
                    AboundApiVersion.V2))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testAboundConfigValidatesEnvironment() {
    assertThatThrownBy(
            () ->
                new AboundConfig(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    null,
                    AboundApiVersion.V2))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testAboundConfigValidatesApiVersion() {
    assertThatThrownBy(
            () ->
                new AboundConfig(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    AboundEnvironment.SANDBOX,
                    null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testValidAboundConfig() {
    final String appId = UUID.randomUUID().toString();
    final String appSecret = UUID.randomUUID().toString();

    final AboundConfig validConfig =
        new AboundConfig(appId, appSecret, AboundEnvironment.SANDBOX, AboundApiVersion.V2);

    assertThat(validConfig.getAppId()).isEqualTo(appId);
    assertThat(validConfig.getAppSecret()).isEqualTo(appSecret);
    assertThat(validConfig.getEnvironment()).isEqualTo(AboundEnvironment.SANDBOX);
    assertThat(validConfig.getApiVersion()).isEqualTo(AboundApiVersion.V2);
  }
}
