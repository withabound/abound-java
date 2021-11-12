package com.withabound.http;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.Objects;
import okhttp3.HttpUrl;

public class HttpUtils {
  /**
   * @param url without query params
   * @param params POJO that will be converted to query params
   * @return a url decorated with the query params
   */
  public static String appendQueryParams(final String url, final AboundQueryParameters params) {
    if (params == null) {
      return url;
    }

    final HttpUrl.Builder urlBuilder = HttpUrl.get(url).newBuilder();

    params.asMap().entrySet().stream()
        .filter(queryParamKeyAndValue -> Objects.nonNull(queryParamKeyAndValue.getValue()))
        .forEach(
            queryParamKeyAndValue ->
                urlBuilder.addQueryParameter(
                    queryParamKeyAndValue.getKey(), queryParamKeyAndValue.getValue().toString()));

    return urlBuilder.toString();
  }
}
