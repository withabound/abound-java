package com.withabound.models.incomes;

import lombok.Getter;

@Getter
public class Income extends IncomeRequest {
  private String incomeId;

  Income(
      IncomeType incomeType,
      Double amount,
      String date,
      String description,
      String category,
      String foreignId) {
    super(incomeType, amount, date, description, category, foreignId);
  }
}
