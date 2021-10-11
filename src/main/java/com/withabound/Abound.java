package com.withabound;

import okhttp3.OkHttpClient;

public final class Abound {
  private final AboundConfig aboundConfig;
  private final OkHttpClient httpClient;

  /** Creates an {@link Abound} client with the default {@link OkHttpClient} settings */
  public Abound(final AboundConfig aboundConfig) {
    this(aboundConfig, new OkHttpClient());
  }

  /**
   * Creates an {@link Abound} client, allowing you to customize the {@link OkHttpClient} settings.
   *
   * <p>See
   * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/#customize-your-client-with-newbuilder
   */
  public Abound(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    this.aboundConfig = aboundConfig;
    this.httpClient = httpClient;
  }
}
