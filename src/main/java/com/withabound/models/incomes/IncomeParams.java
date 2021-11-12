package com.withabound.models.incomes;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IncomeParams extends AboundQueryParameters {
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

  /** An identifier used to filter a list of incomes by their type. */
  private IncomeType incomeType;

  @Override
  public Map<String, Object> asMap() {
    final Map<String, Object> queryParams = new HashMap<>();

    final String incomeTypeStr = Optional.ofNullable(incomeType).map(Object::toString).orElse(null);

    queryParams.put("foreignId", foreignId);
    queryParams.put("page", page);
    queryParams.put("incomeType", incomeTypeStr);

    return queryParams;
  }
}
