package com.withabound.models.payers;

public class Payer extends PayerRequest {
  private String payerId;

  public Payer(
      final String name,
      final String address,
      final String address2,
      final String city,
      final String state,
      final String country,
      final String zipcode,
      final String phoneNumber,
      final String taxIdNumber,
      final String foreignId) {
    super(
        name,
        address,
        address2,
        city,
        state,
        country,
        zipcode,
        phoneNumber,
        taxIdNumber,
        foreignId);
  }

  public String getPayerId() {
    return payerId;
  }
}
