package com.withabound.models.expenses;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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

  private JsonElement notes;

  public Double getAmount() {
    return amount;
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

  public Optional<JsonElement> getNotes() {
    return Optional.ofNullable(notes);
  }

  public void setNotes(final String notes) {
    this.notes = new JsonPrimitive(notes);
  }

  public void setNotes(final JsonObject notes) {
    this.notes = notes;
  }

  @SuppressWarnings({"unused "})
  public static class ExpenseRequestBuilder {
    private JsonElement notes;

    public ExpenseRequestBuilder notes(final String notes) {
      this.notes = new JsonPrimitive(notes);
      return this;
    }

    public ExpenseRequestBuilder notes(final JsonObject notes) {
      this.notes = notes;
      return this;
    }
  }
}
