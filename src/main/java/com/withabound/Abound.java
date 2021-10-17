package com.withabound;

import com.withabound.resources.Users;
import lombok.Getter;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Accessors(fluent = true) // `new Abound(...).users()`, rather than `new Abound(...).getUsers()`
@Getter
public final class Abound {
  private final Users users;

  /** Creates an {@link Abound} client with the default {@link OkHttpClient} settings */
  public Abound(final AboundConfig aboundConfig) {
    this(aboundConfig, new OkHttpClient.Builder());
  }

  /**
   * Creates an {@link Abound} client, allowing you to customize the {@link OkHttpClient} settings.
   *
   * <p>See
   * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/#customize-your-client-with-newbuilder
   */
  public Abound(final AboundConfig aboundConfig, final OkHttpClient.Builder httpClientBuilder) {
    final OkHttpClient httpClient = decorateAndBuildHttpClient(aboundConfig, httpClientBuilder);

    this.users = new Users(aboundConfig, httpClient);
  }

  /**
   * Decorates the OkHttp client with an interceptor that appends request headers
   *
   * @param aboundConfig a valid Abound configuration object
   * @param httpClientBuilder the http client to be decorated
   * @return the decorated, built OkHttp client
   */
  private static OkHttpClient decorateAndBuildHttpClient(
      final AboundConfig aboundConfig, final OkHttpClient.Builder httpClientBuilder) {

    httpClientBuilder.addInterceptor(
        chain -> {
          final Request original = chain.request();
          final String bearerToken =
              String.format("Bearer %s.%s", aboundConfig.getAppId(), aboundConfig.getAppSecret());

          final Request request =
              original
                  .newBuilder()
                  .header("Authorization", bearerToken)
                  .header("Content-Type", "application/json")
                  .build();

          return chain.proceed(request);
        });

    return httpClientBuilder.build();
  }
}
