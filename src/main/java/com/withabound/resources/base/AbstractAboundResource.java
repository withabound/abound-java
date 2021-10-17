package com.withabound.resources.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.withabound.AboundConfig;
import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Base resource from which all other Abound Resources shall extend that maps an SDK action (e.g.
 * update, retrieve, list) to an HTTP verb (e.g. GET, POST, PUT).
 *
 * @param <I> the data type of the request body
 * @param <O> the data type of the payloads returned by SDK methods
 */
public abstract class AbstractAboundResource<I, O> {
  private static final MediaType JSON = MediaType.parse("application/json");
  private static final Gson GSON = new Gson();
  private final Class<O> clazz;

  protected final AboundConfig aboundConfig;
  private final OkHttpClient httpClient;

  protected abstract String getPath();

  protected AbstractAboundResource(
      final AboundConfig aboundConfig, final OkHttpClient httpClient, final Class<O> clazz) {
    this.aboundConfig = aboundConfig;
    this.httpClient = httpClient;
    this.clazz = clazz;
  }

  protected AboundResponse<O> create(final String url, final Map<String, I> requestPayload)
      throws IOException {
    final Request request = httpPost(url, requestPayload);

    return performRequest(request);
  }

  protected AboundBulkResponse<O> list(final String url) throws IOException {
    final Request request = httpGet(url);

    return performBulkRequest(request);
  }

  protected AboundResponse<O> retrieve(final String url) throws IOException {
    final Request request = httpGet(url);

    return performRequest(request);
  }

  private AboundResponse<O> performRequest(final Request request) throws IOException {
    try (Response response = httpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return GSON.fromJson(
            response.body().string(),
            TypeToken.getParameterized(AboundResponse.class, clazz).getType());
      }

      // TODO throw an ApiException
      return null;
    }
  }

  private AboundBulkResponse<O> performBulkRequest(final Request request) throws IOException {
    try (Response response = httpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return GSON.fromJson(
            response.body().string(),
            TypeToken.getParameterized(AboundBulkResponse.class, clazz).getType());
      }

      // TODO throw an ApiException
      return null;
    }
  }

  private static Request httpGet(final String url) {
    return new Request.Builder().get().url(url).build();
  }

  private Request httpPost(final String url, final Map<String, I> rawRequestBody) {
    final RequestBody requestBody = RequestBody.Companion.create(GSON.toJson(rawRequestBody), JSON);

    return new Request.Builder().post(requestBody).url(url).build();
  }
}
