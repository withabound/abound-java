package com.withabound.resources.base;

import java.util.Optional;

/** The response body returned from Abound's APIs when the status code is 4xx or 5xx. */
public class AboundErrorResponse {
  private String message;

  private RequestMetadata request;

  public String getMessage() {
    return message;
  }

  public Optional<RequestMetadata> getRequest() {
    return Optional.ofNullable(request);
  }
}
