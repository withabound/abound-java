package com.withabound.resources.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.withabound.AboundConfig;
import com.withabound.exceptions.AboundApiException;
import com.withabound.http.HttpUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
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
abstract class AbstractAboundResource<I, O> {
  private static final MediaType JSON = MediaType.parse("application/json");
  private static final Gson GSON = new Gson();

  private final Type singleType;
  private final Type bulkType;

  protected final AboundConfig aboundConfig;
  private final OkHttpClient httpClient;

  /** @return the endpoint path of the resource. e.g., "/users", "/documents", etc. */
  protected abstract String getPath();

  /**
   * @param aboundConfig a valid config object
   * @param httpClient the OkHttpClient responsible for issuing network calls
   * @param clazz the explicit class used to unmarshal raw JSON to a POJO. This should be the class
   *     of the <O> type parameter, and is required due to type erasure in Java.
   */
  protected AbstractAboundResource(
      final AboundConfig aboundConfig, final OkHttpClient httpClient, final Class<O> clazz) {
    this.aboundConfig = aboundConfig;
    this.httpClient = httpClient;

    this.singleType = TypeToken.getParameterized(AboundResponse.class, clazz).getType();
    this.bulkType = TypeToken.getParameterized(AboundBulkResponse.class, clazz).getType();
  }

  protected AboundResponse<O> create(final String url, final Map<String, I> requestPayload)
      throws IOException {
    final Request request = httpPost(url, requestPayload);

    return performRequestAndHandleSingleElementResponse(request);
  }

  protected AboundBulkResponse<O> bulkCreate(
      final String url, final Map<String, List<I>> requestPayload) throws IOException {
    final Request request = httpPost(url, requestPayload);

    return performRequestAndHandleBulkElementsResponse(request);
  }

  protected AboundBulkResponse<O> list(final String url) throws IOException {
    return list(url, EmptyQueryParameters.getInstance());
  }

  protected AboundBulkResponse<O> list(final String url, final AboundQueryParameters params)
      throws IOException {
    final Request request = httpGet(url, params);

    return performRequestAndHandleBulkElementsResponse(request);
  }

  protected AboundResponse<O> retrieve(final String url) throws IOException {
    final Request request = httpGet(url, null);

    return performRequestAndHandleSingleElementResponse(request);
  }

  protected AboundResponse<O> update(final String url, final Map<String, I> requestPayload)
      throws IOException {
    final Request request = httpPut(url, requestPayload);

    return performRequestAndHandleSingleElementResponse(request);
  }

  protected AboundResponse<EmptyJsonObject> delete(final String url) throws IOException {
    final Request request = httpDelete(url);

    return sendRequest(
        request, TypeToken.getParameterized(AboundResponse.class, EmptyJsonObject.class).getType());
  }

  /**
   * Performs the {@link Request} and deserializes the response body to {@link AboundResponse}. Use
   * this method when the API returns one element in its `data` field.
   */
  private AboundResponse<O> performRequestAndHandleSingleElementResponse(final Request request)
      throws IOException {
    return sendRequest(request, singleType);
  }

  /**
   * Performs the {@link Request} and deserializes the response body to {@link AboundBulkResponse}.
   * Use this method when the API returns a list of elements in its `data` field.
   */
  private AboundBulkResponse<O> performRequestAndHandleBulkElementsResponse(final Request request)
      throws IOException {
    return sendRequest(request, bulkType);
  }

  /**
   * Makes the HTTP call, deserializing the response body to the appropriate single or bulk type.
   */
  private <RESP> RESP sendRequest(final Request request, final Type serializationType)
      throws IOException {
    try (Response response = httpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return GSON.fromJson(response.body().string(), serializationType);
      }

      final AboundErrorResponse error =
          GSON.fromJson(response.body().string(), AboundErrorResponse.class);

      throw new AboundApiException(error, response.code());
    }
  }

  private static Request httpGet(final String url, final AboundQueryParameters params) {
    final String urlWithParams = HttpUtils.appendQueryParams(url, params);

    return new Request.Builder().get().url(urlWithParams).build();
  }

  private Request httpPost(final String url, final Map<String, ?> rawRequestBody) {
    final RequestBody requestBody = RequestBody.Companion.create(GSON.toJson(rawRequestBody), JSON);

    return new Request.Builder().post(requestBody).url(url).build();
  }

  private Request httpPut(final String url, final Map<String, I> rawRequestBody) {
    final RequestBody requestBody = RequestBody.Companion.create(GSON.toJson(rawRequestBody), JSON);

    return new Request.Builder().put(requestBody).url(url).build();
  }

  private static Request httpDelete(final String url) {
    return new Request.Builder().delete().url(url).build();
  }
}
