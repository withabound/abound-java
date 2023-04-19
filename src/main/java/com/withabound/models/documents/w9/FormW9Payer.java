package com.withabound.models.documents.w9;

import java.util.Optional;

public class FormW9Payer {
  private String name;
  private String address;
  private String address2;
  private String city;
  private String state;
  private String zipcode;

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

  public String getZipcode() {
    return zipcode;
  }
}
