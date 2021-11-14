package com.withabound.models.documents.account_statements;

import lombok.Builder;
import lombok.Setter;

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
