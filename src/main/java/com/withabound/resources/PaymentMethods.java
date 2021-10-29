package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.payment_methods.PaymentMethod;
import com.withabound.models.payment_methods.PaymentMethodRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/payment-methods */
public class PaymentMethods extends AboundUserScopedResource<PaymentMethodRequest, PaymentMethod> {
  public PaymentMethods(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, PaymentMethod.class);
  }

  @Override
  protected String getPath() {
    return "/paymentMethods";
  }

  public AboundResponse<PaymentMethod> create(
      final String userId, final PaymentMethodRequest toCreate) throws IOException {
    final Map<String, PaymentMethodRequest> requestPayload =
        Collections.singletonMap("paymentMethod", toCreate);

    return super.createForUser(userId, requestPayload);
  }

  @Override
  public AboundBulkResponse<PaymentMethod> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  public AboundResponse<PaymentMethod> retrieve(final String userId, final String paymentMethodId)
      throws IOException {
    return super.retrieveForUser(userId, paymentMethodId);
  }
}
