package com.withabound;

import com.withabound.resources.AccessTokens;
import com.withabound.resources.Documents;
import com.withabound.resources.Mailings;
import com.withabound.resources.Payers;
import com.withabound.resources.Users;
import lombok.Getter;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Accessors(fluent = true) // `new Abound(...).users()`, rather than `new Abound(...).getUsers()`
@Getter
public final class Abound {
  private final AccessTokens accessTokens;
  private final Documents documents;
  private final Mailings mailings;
  private final Payers payers;
  private final Users users;

  /** Creates an {@link Abound} client with the default {@link OkHttpClient} settings */
  public Abound(final AboundConfig aboundConfig) {
    this(aboundConfig, new OkHttpClient.Builder());
  }

  /**
   * Creates an {@link Abound} client, allowing you to customize the {@link OkHttpClient} settings.
   * This could be preferable if the default OkHttp settings do not meet your requirements for
   * connection pooling, request timeouts, etc.
   *
   * <p>See
   * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/#customize-your-client-with-newbuilder
   */
  public Abound(final AboundConfig aboundConfig, final OkHttpClient.Builder httpClientBuilder) {
    final OkHttpClient httpClient = decorateAndBuildHttpClient(aboundConfig, httpClientBuilder);

    this.accessTokens = new AccessTokens(aboundConfig, httpClient);
    this.documents = new Documents(aboundConfig, httpClient);
    this.mailings = new Mailings(aboundConfig, httpClient);
    this.payers = new Payers(aboundConfig, httpClient);
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
          final String aboundSDK = String.format("JavaSDK/%s", Version.getSDKVersion());

          final Request request =
              original
                  .newBuilder()
                  .header("Abound-SDK", aboundSDK)
                  .header("Authorization", bearerToken)
                  .header("Content-Type", "application/json")
                  .build();

          return chain.proceed(request);
        });

    return httpClientBuilder.build();
  }
}
