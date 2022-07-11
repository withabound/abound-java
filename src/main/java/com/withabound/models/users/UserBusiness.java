package com.withabound.models.users;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class UserBusiness {
  private String ein;
  private String name;
  private TaxClassification taxClassification;
  private String address;
  private String address2;
  private String city;
  private String state;
  private String zipcode;
  private String country;

  public String getEin() {
    return ein;
  }

  public String getName() {
    return name;
  }

  public Optional<TaxClassification> getTaxClassification() {
    return Optional.ofNullable(taxClassification);
  }

  public Optional<String> getAddress() {
    return Optional.ofNullable(address);
  }

  public Optional<String> getAddress2() {
    return Optional.ofNullable(address2);
  }

  public Optional<String> getCity() {
    return Optional.ofNullable(city);
  }

  public Optional<String> getState() {
    return Optional.ofNullable(state);
  }

  public Optional<String> getZipcode() {
    return Optional.ofNullable(zipcode);
  }

  public Optional<String> getCountry() {
    return Optional.ofNullable(country);
  }
}
