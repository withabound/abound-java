package com.withabound.resources.base;

/** The response body returned from Abound's APIs when the status code is 4xx or 5xx. */
public class AboundErrorResponse {
  private String message;

  public String getMessage() {
    return message;
  }
}
