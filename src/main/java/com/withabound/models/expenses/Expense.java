package com.withabound.models.expenses;

import lombok.Getter;

/** See https://docs.withabound.com/reference/expenses */
@Getter
public class Expense extends ExpenseRequest {
  private String expenseId;

  private Double deductionAmount;

  private ExpensePredictions predictions;

  public Expense(final Double amount, final String description, final String date) {
    super(amount, description, date);
  }
}
