package com.withabound.models.mailings;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class MailingRequest {
  /** Used for testing in sandbox. This will be ignored in all production requests. */
  private String test_status;

  public Optional<String> getTest_Status() {
    return Optional.ofNullable(test_status);
  }
}
