package com.withabound.exceptions;

import com.withabound.resources.base.AboundErrorResponse;
import com.withabound.resources.base.RequestMetadata;
import java.util.Optional;

/** Thrown when an error response from Abound's APIs is encountered. */
public class AboundApiException extends RuntimeException {
  private final int statusCode;
  private final RequestMetadata request;

  public AboundApiException(final AboundErrorResponse errorResponse, final int statusCode) {
    super(errorResponse.getMessage());
    this.statusCode = statusCode;
    this.request = errorResponse.getRequest().orElse(null);
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Optional<RequestMetadata> getRequest() {
    return Optional.ofNullable(request);
  }
}
