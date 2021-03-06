package com.withabound.models.incomes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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

  private IncomeDocumentType documentType;

  private JsonElement notes;

  public Optional<IncomeType> getIncomeType() {
    return Optional.ofNullable(incomeType);
  }

  public Double getAmount() {
    return amount;
  }

  public String getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public Optional<String> getCategory() {
    return Optional.ofNullable(category);
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }

  public Optional<IncomeDocumentType> getDocumentType() {
    return Optional.ofNullable(documentType);
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
  public static class IncomeRequestBuilder {
    private JsonElement notes;

    public IncomeRequestBuilder notes(final String notes) {
      this.notes = new JsonPrimitive(notes);
      return this;
    }

    public IncomeRequestBuilder notes(final JsonObject notes) {
      this.notes = notes;
      return this;
    }
  }
}
