package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.tax_payments.TaxPayment;
import com.withabound.models.tax_payments.TaxPaymentParams;
import com.withabound.models.tax_payments.TaxPaymentRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * See https://docs.withabound.com/reference/tax-payments
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
public class TaxPayments extends AboundUserScopedResource<TaxPaymentRequest, TaxPayment> {
  public TaxPayments(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, TaxPayment.class);
  }

  @Override
  protected String getPath() {
    return "/taxPayments";
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<TaxPayment> create(final String userId, final TaxPaymentRequest toCreate)
      throws IOException {
    final Map<String, TaxPaymentRequest> requestBody =
        Collections.singletonMap("taxPayment", toCreate);

    return super.createForUser(userId, requestBody);
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
  public AboundBulkResponse<TaxPayment> list(final String userId) throws IOException {
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
  public AboundBulkResponse<TaxPayment> list(final String userId, final TaxPaymentParams params)
      throws IOException {
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
  public AboundResponse<TaxPayment> retrieve(final String userId, final String taxPaymentId)
      throws IOException {
    return super.retrieveForUser(userId, taxPaymentId);
  }
}
