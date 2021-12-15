package com.withabound.models.incomes;

import com.google.gson.JsonElement;
import com.withabound.models.documents.DocumentType;
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
      final String foreignId,
      final DocumentType documentType,
      final JsonElement notes) {
    super(incomeType, amount, date, description, category, foreignId, documentType, notes);
  }
}
