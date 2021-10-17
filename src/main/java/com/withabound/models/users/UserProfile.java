package com.withabound.models.users;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class UserProfile {
  private String firstName;
  private String lastName;
  private String address;
  private String address2;
  private String city;
  private String state;
  private String zipcode;
  private String phoneNumber; // no country code, numerical digits only
  private String dateOfBirth; // YYYY-MM-DD
  private String socialSecurityNumber; // no hyphens, numerical digits only
  private String ipAddress;

  public Optional<String> getFirstName() {
    return Optional.ofNullable(firstName);
  }

  public Optional<String> getLastName() {
    return Optional.ofNullable(lastName);
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

  public Optional<String> getPhoneNumber() {
    return Optional.ofNullable(phoneNumber);
  }

  public Optional<String> getDateOfBirth() {
    return Optional.ofNullable(dateOfBirth);
  }

  public Optional<String> getSocialSecurityNumber() {
    return Optional.ofNullable(socialSecurityNumber);
  }

  public Optional<String> getIpAddress() {
    return Optional.ofNullable(ipAddress);
  }
}
