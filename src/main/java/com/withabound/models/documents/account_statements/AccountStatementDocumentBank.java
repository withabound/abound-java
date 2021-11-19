package com.withabound.models.documents.account_statements;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class AccountStatementDocumentBank {
  private String name;

  private String logo; // base64 encoded logo

  private String address;

  private String address2;

  private String city;

  private String state; // two-letter code

  private String zipcode;

  private AccountStatementDocumentBank.CustomerService customerService;

  public String getName() {
    return name;
  }

  public String getLogo() {
    return logo;
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

  public Optional<AccountStatementDocumentBank.CustomerService> getCustomerService() {
    return Optional.ofNullable(customerService);
  }

  @Builder
  @Setter
  public static class CustomerService {
    private String phoneNumber;

    private String email;

    private String website;

    private String instructions;

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public String getEmail() {
      return email;
    }

    public String getWebsite() {
      return website;
    }

    public Optional<String> getInstructions() {
      return Optional.ofNullable(instructions);
    }
  }
}
