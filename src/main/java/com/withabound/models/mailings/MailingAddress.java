package com.withabound.models.mailings;

import java.util.Optional;
import lombok.Getter;

@Getter
public class MailingAddress {
  private String name;

  private String company;

  private String address;

  private String address2;

  private String city;

  private String state;

  private String zipcode;

  private String country;

  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  public Optional<String> getCompany() {
    return Optional.ofNullable(company);
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
