package com.withabound.models.taxes;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Setter;

/** See https://docs.withabound.com/reference/taxes */
@Builder
@Setter
public class TaxRequest {
  @SerializedName("1099Income")
  private Double ten99Income; // The total annual 1099 income for the user

  private Double w2Income; // The user’s expected annual W-2 income (if applicable).

  private Double expenseDeduction; // The total business expense deduction for this user.

  private Double mileage; // The total number of tax deductible miles a user has driven YTD.

  // lowercase two-letter code. The state in which the user earns their 1099 income.
  // (this is typically the state they live in).
  private String filingState;

  private FilingStatus filingStatus;

  public Double get1099Income() {
    return ten99Income;
  }

  public Double getW2Income() {
    return w2Income;
  }

  public Double getExpenseDeduction() {
    return expenseDeduction;
  }

  public Double getMileage() {
    return mileage;
  }

  public String getFilingState() {
    return filingState;
  }

  public FilingStatus getFilingStatus() {
    return filingStatus;
  }
}
