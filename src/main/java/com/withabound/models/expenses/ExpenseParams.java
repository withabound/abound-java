package com.withabound.models.expenses;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExpenseParams extends AboundQueryParameters {
  /**
   * A customer-specific unique identifier associated to an Abound record. Allows developers to
   * forego making their own tables every time they integrate and/or add an application.
   */
  private String foreignId;

  /**
   * An identifier used to request a specific page of results. Responses are limited to a maximum of
   * 100 records.
   */
  private String page;

  /** Filters the list of expenses based on the year field. */
  private String year;

  @Override
  public Map<String, Object> asMap() {
    final Map<String, Object> queryParams = new HashMap<>();

    queryParams.put("foreignId", foreignId);
    queryParams.put("page", page);
    queryParams.put("year", year);

    return queryParams;
  }
}
