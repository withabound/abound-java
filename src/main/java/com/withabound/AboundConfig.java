package com.withabound;

import org.apache.commons.lang3.StringUtils;

public class AboundConfig {
  private final String appId;
  private final String appSecret;
  private final AboundEnvironment environment;
  private final AboundApiVersion apiVersion;

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
  }

  public String getAppId() {
    return appId;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public AboundEnvironment getEnvironment() {
    return environment;
  }

  public AboundApiVersion getApiVersion() {
    return apiVersion;
  }

  /** Validates that the {@link AboundConfig} has been constructed with all required fields. */
  private void validate() {
    if (StringUtils.isEmpty(appId)) {
      throw new IllegalArgumentException("appId cannot be empty!");
    }

    if (StringUtils.isEmpty(appSecret)) {
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
