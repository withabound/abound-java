package com.withabound.models.tax_payments;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class TaxPaymentRequest {
  private String year;

  private TaxPeriod period;

  private Double amount;

  private TaxPaymentEntity entity;

  private String paymentMethodId;

  // TODO
  //  private Notes notes;

  public String getYear() {
    return year;
  }

  public TaxPeriod getPeriod() {
    return period;
  }

  public Double getAmount() {
    return amount;
  }

  public TaxPaymentEntity getEntity() {
    return entity;
  }

  public String getPaymentMethodId() {
    return paymentMethodId;
  }
}
