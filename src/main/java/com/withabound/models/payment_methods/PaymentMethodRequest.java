package com.withabound.models.payment_methods;

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

  // TODO see ExpenseRequest
  //  private Notes notes;

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
}
