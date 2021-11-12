package com.withabound.models.payment_methods;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.Collections;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaymentMethodParams extends AboundQueryParameters {
  /**
   * An identifier used to request a specific page of results. Responses are limited to a maximum of
   * 100 records.
   */
  private String page;

  @Override
  public Map<String, Object> asMap() {
    return Collections.singletonMap("page", page);
  }
}
