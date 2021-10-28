package com.withabound.resources.base;

import com.withabound.AboundConfig;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * Abstract class for all user-scoped resources, wherein a user-scoped resource is one having the
 * relative path `https://baseURL/v2/users/{userId}/{resourceName}`
 *
 * @param <I> input — the data type of the request body
 * @param <O> output — the data type of the payloads returned by SDK methods.
 */
public abstract class AboundUserScopedResource<I, O> extends AbstractAboundResource<I, O> {
  protected AboundUserScopedResource(
      final AboundConfig aboundConfig, final OkHttpClient httpClient, final Class<O> clazz) {
    super(aboundConfig, httpClient, clazz);
  }

  protected AboundBulkResponse<O> listForUser(final String userId) throws IOException {
    final String url = getUserScopedResourcesUrl(userId);

    return super.list(url);
  }

  protected AboundResponse<O> retrieveForUser(final String userId, final String id)
      throws IOException {
    final String url = getUserScopedResourceUrl(userId, id);

    return super.retrieve(url);
  }

  protected AboundResponse<O> createForUser(
      final String userId, final Map<String, I> requestPayload) throws IOException {
    final String url = getUserScopedResourcesUrl(userId);

    return super.create(url, requestPayload);
  }

  protected AboundBulkResponse<O> bulkCreateForUser(
      final String userId, final Map<String, List<I>> requestPayload) throws IOException {
    final String url = getUserScopedResourcesUrl(userId);

    return super.bulkCreate(url, requestPayload);
  }

  protected AboundResponse<O> updateForUser(
      final String userId, final String id, final Map<String, I> requestPayload)
      throws IOException {
    final String url = getUserScopedResourceUrl(userId, id);

    return super.update(url, requestPayload);
  }

  protected AboundResponse<EmptyJsonObject> deleteForUser(final String userId, final String id)
      throws IOException {
    final String url = getUserScopedResourceUrl(userId, id);

    return super.delete(url);
  }

  /** @return e.g., "https://baseURL/v2/users/{userId}/{resourceName}" */
  private String getUserScopedResourcesUrl(final String userId) {
    return String.format("%s%s%s%s", aboundConfig.getBaseUrl(), "/users/", userId, getPath());
  }

  /** @return e.g., "https://baseURL/v2/users/{userId}/{resourceName}/{resourceId}" */
  private String getUserScopedResourceUrl(final String userId, final String id) {
    return String.format(
        "%s%s%s%s/%s", aboundConfig.getBaseUrl(), "/users/", userId, getPath(), id);
  }
}
