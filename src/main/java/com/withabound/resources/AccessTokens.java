package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.access_tokens.AccessToken;
import com.withabound.models.access_tokens.AccessTokenRequest;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/access-tokens */
public class AccessTokens extends AboundBaseResource<AccessTokenRequest, AccessToken> {
  @Override
  protected String getPath() {
    return "/accessTokens";
  }

  public AccessTokens(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, AccessToken.class);
  }

  public AboundResponse<AccessToken> create(final AccessTokenRequest toCreate) throws IOException {
    final Map<String, AccessTokenRequest> requestPayload =
        Collections.singletonMap("accessToken", toCreate);

    return super.create(requestPayload);
  }
}
