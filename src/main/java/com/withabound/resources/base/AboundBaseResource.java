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
    final String url = String.format("%s%s", aboundConfig.getBaseUrl(), getPath());

    return super.create(url, requestPayload);
  }

  protected AboundBulkResponse<O> list() throws IOException {
    final String url = String.format("%s%s", aboundConfig.getBaseUrl(), getPath());

    return super.list(url);
  }

  @Override
  protected AboundResponse<O> retrieve(final String id) throws IOException {
    final String url = String.format("%s%s/%s", aboundConfig.getBaseUrl(), getPath(), id);

    return super.retrieve(url);
  }
}
