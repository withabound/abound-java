package com.withabound.models.incomes;

import com.google.gson.annotations.SerializedName;

public enum IncomeDocumentType {
  @SerializedName("1099int")
  TEN99INT("1099int"),

  @SerializedName("1099k")
  TEN99K("1099k"),

  @SerializedName("1099misc")
  TEN99MISC("1099misc"),

  @SerializedName("1099nec")
  TEN99NEC("1099nec"),

  @SerializedName("ssa1099")
  SSA1099("ssa1099");

  private final String name;

  IncomeDocumentType(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
