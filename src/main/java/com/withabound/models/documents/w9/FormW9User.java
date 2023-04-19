package com.withabound.models.documents.w9;

import java.util.Optional;

public class FormW9User {
  private String name;
  private String businessName;
  private String address;
  private String address2;
  private String city;
  private String state;
  private String zipcode;
  private String country;

  public String getName() {
    return name;
  }

  public Optional<String> getBusinessName() {
    return Optional.ofNullable(businessName);
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

  public Optional<String> getCountry() {
    return Optional.ofNullable(country);
  }
}
