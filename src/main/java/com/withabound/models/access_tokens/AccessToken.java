package com.withabound.models.access_tokens;

public class AccessToken {
  private String accessToken;
  private Long expirationTimestamp;

  public String getAccessToken() {
    return accessToken;
  }

  public Long getExpirationTimestamp() {
    return expirationTimestamp;
  }
}
