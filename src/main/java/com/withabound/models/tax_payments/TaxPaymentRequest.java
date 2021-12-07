package com.withabound.models.tax_payments;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Optional;
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

  private JsonElement notes;

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

  public Optional<JsonElement> getNotes() {
    return Optional.ofNullable(notes);
  }

  public void setNotes(final String notes) {
    this.notes = new JsonPrimitive(notes);
  }

  public void setNotes(final JsonObject notes) {
    this.notes = notes;
  }

  @SuppressWarnings({"unused "})
  public static class TaxPaymentRequestBuilder {
    private JsonElement notes;

    public TaxPaymentRequestBuilder notes(final String notes) {
      this.notes = new JsonPrimitive(notes);
      return this;
    }

    public TaxPaymentRequestBuilder notes(final JsonObject notes) {
      this.notes = notes;
      return this;
    }
  }
}
