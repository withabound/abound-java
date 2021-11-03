package com.withabound.models.incomes;

import lombok.Getter;

/** See https://docs.withabound.com/reference/incomes */
@Getter
public class Income extends IncomeRequest {
  private String incomeId;

  Income(
      final IncomeType incomeType,
      final Double amount,
      final String date,
      final String description,
      final String category,
      final String foreignId) {
    super(incomeType, amount, date, description, category, foreignId);
  }
}
