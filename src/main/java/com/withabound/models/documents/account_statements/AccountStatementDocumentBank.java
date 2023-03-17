package com.withabound.models.documents.account_statements;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/**
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
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

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
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
