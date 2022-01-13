package com.withabound;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VersionTest {
  @Test
  public void testGetSDKVersionLoadsVersionFromFile() {
    final String result = Version.getSDKVersion();

    assertThat(result).isNotEmpty();
    assertThat(result).isNotEqualTo("unknown");
    assertThat(Character.isDigit(result.charAt(0))).isTrue();
  }
}
