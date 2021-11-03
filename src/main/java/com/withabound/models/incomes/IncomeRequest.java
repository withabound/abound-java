package com.withabound.models.incomes;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/** See https://docs.withabound.com/reference/incomes */
@Builder
@Setter
public class IncomeRequest {
  private IncomeType incomeType;

  private Double amount;

  private String date; // YYYY-MM-DD

  private String description;

  private String category;

  private String foreignId;

  // TODO handle Notes (de)serialization
  //  private Notes notes;

  public IncomeType getIncomeType() {
    return incomeType;
  }

  public Double getAmount() {
    return amount;
  }

  public String getDate() {
    return date;
  }

  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  public Optional<String> getCategory() {
    return Optional.ofNullable(category);
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }
}
