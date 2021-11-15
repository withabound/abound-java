package com.withabound.http;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.Objects;
import okhttp3.HttpUrl;

public class HttpUtils {
  /**
   * A noteworthy limitation of this method is that it cannot support multiple query parameters with
   * the same key. For example, the styles ?list=red,blue,green and ?list=red&list=blue&list=green
   * are both valid (https://datatracker.ietf.org/doc/html/rfc6570#section-3.2.8) and strive to
   * convey the same meaning, but this method will currently not handle either of those strategies.
   * At time of writing, Abound's APIs do not support logical OR filtering of the same query
   * parameter key, so this feature is not yet required.
   *
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
