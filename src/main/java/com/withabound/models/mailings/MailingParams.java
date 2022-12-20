package com.withabound.models.mailings;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MailingParams extends AboundQueryParameters {
  /**
   * An identifier used to request a specific page of results. Responses are limited to a maximum of
   * 100 records.
   */
  private String page;

  @Override
  public Map<String, Object> asMap() {
    final Map<String, Object> queryParams = new HashMap<>();

    queryParams.put("page", page);

    return queryParams;
  }
}
