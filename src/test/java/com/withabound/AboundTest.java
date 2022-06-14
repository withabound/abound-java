package com.withabound;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

class AboundTest extends AbstractAboundTest {
  @Test
  public void testAboundClientSetsAboundSDKHeader() throws IOException, InterruptedException {
    getMockAboundClient().users().list();

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final String aboundSDKValue = recordedRequest.getHeader("Abound-SDK");

    assertThat(aboundSDKValue).startsWith("JavaSDK/");
    assertThat(aboundSDKValue).doesNotEndWith("null");
    assertThat(aboundSDKValue).doesNotEndWith("unknown");

    // assert the first character after JavaSDK/ is numerical; it's imperfect but fairly reasonable,
    // won't require manual updating upon new versions being released, would pass on both -SNAPSHOT
    // and non-SNAPSHOT versions, etc. as long as the SDK adheres to semantic versioning
    assertThat(Character.isDigit(aboundSDKValue.charAt("JavaSDK/".length()))).isTrue();
  }
}
