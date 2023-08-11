package com.withabound.resources.base;

import com.withabound.AboundConfig;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * Abstract class for all document-scoped resources, wherein a document-scoped resource is one
 * having the relative path
 * `https://baseURL/v3/users/{userId}/documents/{documentId}/{resourceName}`
 *
 * @param <I> input — the data type of the request body
 * @param <O> output — the data type of the payloads returned by SDK methods.
 */
public abstract class AboundDocumentScopedResource<I, O> extends AboundUserScopedResource<I, O> {
  protected AboundDocumentScopedResource(
      final AboundConfig aboundConfig, final OkHttpClient httpClient, final Class<O> clazz) {
    super(aboundConfig, httpClient, clazz);
  }

  protected AboundBulkResponse<O> listForDocument(final String userId, final String documentId)
      throws IOException {
    return listForDocument(userId, documentId, EmptyQueryParameters.getInstance());
  }

  protected AboundBulkResponse<O> listForDocument(
      final String userId, final String documentId, final AboundQueryParameters params)
      throws IOException {
    final String url = getDocumentScopedResourcesUrl(userId, documentId);

    return super.list(url, params);
  }

  protected AboundResponse<O> retrieveForDocument(
      final String userId, final String documentId, final String id) throws IOException {
    final String url = getDocumentScopedResourceUrl(userId, documentId, id);

    return super.retrieve(url);
  }

  protected AboundResponse<O> createForDocument(
      final String userId, final String documentId, final Map<String, I> requestPayload)
      throws IOException {
    final String url = getDocumentScopedResourcesUrl(userId, documentId);

    return super.create(url, requestPayload);
  }

  protected AboundResponse<EmptyJsonObject> deleteForDocument(
      final String userId, final String documentId, final String id) throws IOException {
    final String url = getDocumentScopedResourceUrl(userId, documentId, id);

    return super.delete(url);
  }

  /** @return e.g., "https://baseURL/v3/users/{userId}/documents/{documentId}/{resourceName}" */
  private String getDocumentScopedResourcesUrl(final String userId, final String documentId) {
    return String.format(
        "%s%s%s%s%s%s",
        aboundConfig.getBaseUrl(), "/users/", userId, "/documents/", documentId, getPath());
  }

  /**
   * @return e.g.,
   *     "https://baseURL/v3/users/{userId}/documents/{documentId}/{resourceName}/{resourceId}"
   */
  private String getDocumentScopedResourceUrl(
      final String userId, final String documentId, final String id) {
    return String.format(
        "%s%s%s%s%s%s/%s",
        aboundConfig.getBaseUrl(), "/users/", userId, "/documents/", documentId, getPath(), id);
  }
}
