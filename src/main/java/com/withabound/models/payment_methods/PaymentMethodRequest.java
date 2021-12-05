package com.withabound.models.payment_methods;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/** See https://docs.withabound.com/reference/payment-methods */
@Builder
@Setter
public class PaymentMethodRequest {
  private String accountNumber;

  private String routingNumber;

  private AccountType accountType;

  private AccountClass accountClass;

  private JsonElement notes;

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public AccountClass getAccountClass() {
    return accountClass;
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
  public static class PaymentMethodRequestBuilder {
    private JsonElement notes;

    public PaymentMethodRequestBuilder notes(final String notes) {
      this.notes = new JsonPrimitive(notes);
      return this;
    }

    public PaymentMethodRequestBuilder notes(final JsonObject notes) {
      this.notes = notes;
      return this;
    }
  }
}
