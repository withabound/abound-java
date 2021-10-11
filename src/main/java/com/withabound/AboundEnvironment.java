package com.withabound;

public enum AboundEnvironment {
  SANDBOX("https://sandbox-api.withabound.com/"),
  PRODUCTION("https://production-api.withabound.com/");

  private final String baseUrl;

  AboundEnvironment(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getBaseUrl() {
    return baseUrl;
  }
}
