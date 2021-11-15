package com.withabound.resources.base;

import com.withabound.AboundConfig;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * Abstract class for all base resources, wherein a base resource is one having the relative path
 * `https://baseURL/v2/resource` (i.e. it is not scoped to a user).
 *
 * @param <I> input — the data type of the request body
 * @param <O> output — the data type of the payloads returned by SDK methods.
 */
public abstract class AboundBaseResource<I, O> extends AbstractAboundResource<I, O> {
  protected AboundBaseResource(
      final AboundConfig aboundConfig, final OkHttpClient httpClient, final Class<O> clazz) {
    super(aboundConfig, httpClient, clazz);
  }

  protected AboundResponse<O> create(final Map<String, I> requestPayload) throws IOException {
    final String url = getResourcesUrl();

    return super.create(url, requestPayload);
  }

  protected AboundBulkResponse<O> list() throws IOException {
    return list(EmptyQueryParameters.getInstance());
  }

  protected AboundBulkResponse<O> list(final AboundQueryParameters params) throws IOException {
    final String url = getResourcesUrl();

    return super.list(url, params);
  }

  @Override
  protected AboundResponse<O> retrieve(final String id) throws IOException {
    final String url = getResourceUrl(id);

    return super.retrieve(url);
  }

  /** Updates a single object by issuing a PUT request to /v2/{resourceName}/{resourceId} */
  protected AboundResponse<O> update(final String id, final Map<String, I> requestPayload)
      throws IOException {
    final String url = getResourceUrl(id);

    return super.update(url, requestPayload);
  }

  @Override
  protected AboundResponse<EmptyJsonObject> delete(final String id) throws IOException {
    final String url = getResourceUrl(id);

    return super.delete(url);
  }

  /**
   * @return returns the baseUrl + the resource path. e.g.,
   *     "https://sandbox-api.withabound.com/v2/users"
   */
  private String getResourcesUrl() {
    return String.format("%s%s", aboundConfig.getBaseUrl(), getPath());
  }

  /**
   * @return returns the baseUrl + the resource path + the id of the resource. e.g.,
   *     "https://sandbox-api.withabound.com/v2/users/{userId}"
   */
  private String getResourceUrl(final String id) {
    return String.format("%s%s/%s", aboundConfig.getBaseUrl(), getPath(), id);
  }
}
