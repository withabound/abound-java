package com.withabound.exceptions;

/** Thrown when an error response from Abound's APIs is encountered. */
public class AboundApiException extends RuntimeException {
  private final int statusCode;

  public AboundApiException(final String message, final int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
