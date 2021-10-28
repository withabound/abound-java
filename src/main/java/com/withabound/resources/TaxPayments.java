package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.tax_payments.TaxPayment;
import com.withabound.models.tax_payments.TaxPaymentRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

public class TaxPayments extends AboundUserScopedResource<TaxPaymentRequest, TaxPayment> {
  public TaxPayments(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, TaxPayment.class);
  }

  @Override
  protected String getPath() {
    return "/taxPayments";
  }

  public AboundResponse<TaxPayment> create(final String userId, final TaxPaymentRequest toCreate)
      throws IOException {
    final Map<String, TaxPaymentRequest> requestBody =
        Collections.singletonMap("taxPayment", toCreate);

    return super.createForUser(userId, requestBody);
  }

  @Override
  public AboundBulkResponse<TaxPayment> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  public AboundResponse<TaxPayment> retrieve(final String userId, final String taxPaymentId)
      throws IOException {
    return super.retrieveForUser(userId, taxPaymentId);
  }
}
