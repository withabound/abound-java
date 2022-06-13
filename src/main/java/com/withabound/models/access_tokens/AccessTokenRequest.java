package com.withabound.models.access_tokens;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class AccessTokenRequest {
  private String userId;
  private Long expirationTimestamp;

  public String getUserId() {
    return userId;
  }

  public Optional<Long> getExpirationTimestamp() {
    return Optional.ofNullable(expirationTimestamp);
  }
}
