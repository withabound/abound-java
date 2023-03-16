package com.withabound.models.documents.account_statements;

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
public class AccountStatementDocumentSummary {
  private Double beginningBalance;

  private Double endingBalance;

  private Double interestPercentage;

  private Double interestAmount;

  private Double totalFees;

  public Double getBeginningBalance() {
    return beginningBalance;
  }

  public Double getEndingBalance() {
    return endingBalance;
  }

  public Double getInterestPercentage() {
    return interestPercentage;
  }

  public Double getInterestAmount() {
    return interestAmount;
  }

  public Double getTotalFees() {
    return totalFees;
  }
}
