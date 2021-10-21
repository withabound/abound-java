package com.withabound.models.expenses;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

/** See https://docs.withabound.com/reference/expenses */
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpenseRequest {
  public ExpenseRequest(final Double amount, final String description, final String date) {
    this.amount = amount;
    this.description = description;
    this.date = date;
  }

  private Double amount;

  private String description;

  private String date; // YYYY-MM-DD

  private ExpenseType expenseType;

  private String taxCategory;

  private String foreignId;

  // TODO Notes marshaling/unmarshalling could be complex. this field can be either a JSON object or
  //    a string, so this functionality could be implemented in a separate PR to allow for greater
  //    scrutiny on this topic specifically
  //  private Notes notes;

  public Optional<Double> getAmount() {
    return Optional.ofNullable(amount);
  }

  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  public Optional<String> getDate() {
    return Optional.ofNullable(date);
  }

  public Optional<ExpenseType> getExpenseType() {
    return Optional.ofNullable(expenseType);
  }

  public Optional<String> getTaxCategory() {
    return Optional.ofNullable(taxCategory);
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }
}
