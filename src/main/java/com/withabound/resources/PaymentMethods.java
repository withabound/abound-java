package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.payment_methods.PaymentMethod;
import com.withabound.models.payment_methods.PaymentMethodParams;
import com.withabound.models.payment_methods.PaymentMethodRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * See https://docs.withabound.com/reference/payment-methods
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
public class PaymentMethods extends AboundUserScopedResource<PaymentMethodRequest, PaymentMethod> {
  public PaymentMethods(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, PaymentMethod.class);
  }

  @Override
  protected String getPath() {
    return "/paymentMethods";
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<PaymentMethod> create(
      final String userId, final PaymentMethodRequest toCreate) throws IOException {
    final Map<String, PaymentMethodRequest> requestPayload =
        Collections.singletonMap("paymentMethod", toCreate);

    return super.createForUser(userId, requestPayload);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  @Override
  public AboundBulkResponse<PaymentMethod> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundBulkResponse<PaymentMethod> list(
      final String userId, final PaymentMethodParams params) throws IOException {
    return super.listForUser(userId, params);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<PaymentMethod> retrieve(final String userId, final String paymentMethodId)
      throws IOException {
    return super.retrieveForUser(userId, paymentMethodId);
  }
}
