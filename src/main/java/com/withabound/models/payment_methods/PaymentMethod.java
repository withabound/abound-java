package com.withabound.models.payment_methods;

import com.google.gson.JsonElement;
import java.util.Optional;

/** See https://docs.withabound.com/reference/payment-methods */
public class PaymentMethod extends PaymentMethodRequest {
  private String paymentMethodId;

  private String displayName;

  private String accountNumberLast4;

  private String status;

  public PaymentMethod(
      final String accountNumber,
      final String routingNumber,
      final AccountType accountType,
      final AccountClass accountClass,
      final JsonElement notes) {
    super(accountNumber, routingNumber, accountType, accountClass, notes);
  }

  public String getPaymentMethodId() {
    return paymentMethodId;
  }

  public Optional<String> getDisplayName() {
    return Optional.ofNullable(displayName);
  }

  public Optional<String> getAccountNumberLast4() {
    return Optional.ofNullable(accountNumberLast4);
  }

  public String getStatus() {
    return status;
  }
}
