package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.access_tokens.AccessToken;
import com.withabound.models.access_tokens.AccessTokenRequest;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccessTokensTest extends AbstractAboundTest {
  @Test
  public void testCreate() throws IOException {
    final AccessTokenRequest toCreate =
        AccessTokenRequest.builder().userId(TestUtils.TEST_USER_ID).build();

    final AboundResponse<AccessToken> response = getAboundClient().accessTokens().create(toCreate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    assertThat(response.getData().getAccessToken()).isEqualTo(TestUtils.TEST_ACCESS_TOKEN);
    assertThat(response.getData().getExpirationTimestamp()).isEqualTo(32503701600000L);
  }

  @Test
  public void testCreateWithNullRequestBodyThrowsAboundApiException() {
    Assertions.assertThatThrownBy(() -> getAboundClient().accessTokens().create(null))
        .isInstanceOf(AboundApiException.class)
        .hasMessage("Expected accessToken object, but received undefined")
        .hasFieldOrPropertyWithValue("statusCode", 400)
        .hasFieldOrProperty("request");
  }
}
