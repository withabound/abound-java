package com.withabound.models.users;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserParams extends AboundQueryParameters {
  /**
   * An identifier used to request a specific page of results. Responses are limited to a maximum of
   * 100 records.
   */
  private String page;

  /**
   * A customer-specific unique identifier associated to an Abound record. Allows developers to
   * forego making their own tables every time they integrate and/or add an application.
   */
  private String foreignId;

  @Override
  public Map<String, Object> asMap() {
    final Map<String, Object> queryParams = new HashMap<>();

    queryParams.put("page", page);
    queryParams.put("foreignId", foreignId);

    return queryParams;
  }
}
