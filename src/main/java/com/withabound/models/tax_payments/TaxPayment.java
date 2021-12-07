package com.withabound.models.tax_payments;

import com.google.gson.JsonElement;
import java.util.Optional;

public class TaxPayment extends TaxPaymentRequest {
  private String taxPaymentId;

  private String createdDate;

  private TaxPaymentDocument document;

  private String status;

  TaxPayment(
      final String year,
      final TaxPeriod period,
      final Double amount,
      final TaxPaymentEntity entity,
      final String paymentMethodId,
      final JsonElement notes) {
    super(year, period, amount, entity, paymentMethodId, notes);
  }

  public String getTaxPaymentId() {
    return taxPaymentId;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public Optional<TaxPaymentDocument> getDocument() {
    return Optional.ofNullable(document);
  }

  public String getStatus() {
    return status;
  }
}
