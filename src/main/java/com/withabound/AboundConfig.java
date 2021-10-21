package com.withabound;

import lombok.Getter;

@Getter
public class AboundConfig {
  private final String appId;
  private final String appSecret;
  private final AboundEnvironment environment;
  private final AboundApiVersion apiVersion;
  private final String baseUrl;

  public AboundConfig(
      final String appId,
      final String appSecret,
      final AboundEnvironment environment,
      final AboundApiVersion apiVersion) {
    this.appId = appId;
    this.appSecret = appSecret;
    this.environment = environment;
    this.apiVersion = apiVersion;

    validate();

    this.baseUrl = environment.getBaseUrl() + apiVersion.toString().toLowerCase();
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  /** Validates that the {@link AboundConfig} has been constructed with all required fields. */
  private void validate() {
    if (appId == null || appId.isEmpty()) {
      throw new IllegalArgumentException("appId cannot be empty!");
    }

    if (appSecret == null || appSecret.isEmpty()) {
      throw new IllegalArgumentException("appSecret cannot be empty!");
    }

    if (environment == null) {
      throw new IllegalArgumentException("environment cannot be null!");
    }

    if (apiVersion == null) {
      throw new IllegalArgumentException("apiVersion cannot be null!");
    }
  }
}
