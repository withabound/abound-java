package com.withabound.models.payers;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/** See https://docs.withabound.com/reference/payers */
@Builder
@Setter
public class PayerRequest {
  private String name;

  private String address;

  private String address2;

  private String city;

  /** Two-letter character code for this payer's state. If foreign, use payer's province. */
  private String state;

  /** The payer's country, ISO 3166-2 standards. */
  private String country;

  /** If foreign, use payer's foreign postal code. */
  private String zipcode;

  /** Numerical digits only. */
  private String phoneNumber;

  /**
   * The payer's tax identification number, commonly referred to as a TIN (or an EIN). No hyphens.
   * Numerical digits only.
   */
  private String taxIdNumber;

  /** The customer-specific unique identifier associated with this payer. */
  private String foreignId;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public Optional<String> getAddress2() {
    return Optional.ofNullable(address2);
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getCountry() {
    return country;
  }

  public String getZipcode() {
    return zipcode;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getTaxIdNumber() {
    return taxIdNumber;
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }
}
