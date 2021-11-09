package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.taxes.Tax;
import com.withabound.models.taxes.TaxRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/taxes */
public class Taxes extends AboundUserScopedResource<TaxRequest, Tax> {
  public Taxes(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Tax.class);
  }

  @Override
  protected String getPath() {
    return "/taxes";
  }

  public AboundBulkResponse<Tax> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  public AboundResponse<Tax> retrieve(final String userId, final String year) throws IOException {
    return super.retrieveForUser(userId, year);
  }

  public AboundResponse<Tax> calculate(
      final String userId, final String year, final TaxRequest toCalculate) throws IOException {
    final Map<String, TaxRequest> requestPayload = Collections.singletonMap("taxes", toCalculate);

    return super.updateForUser(userId, year, requestPayload);
  }
}
