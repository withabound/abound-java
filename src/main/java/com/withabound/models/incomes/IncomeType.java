package com.withabound.models.incomes;

import com.google.gson.annotations.SerializedName;

public enum IncomeType {
  @SerializedName("1099")
  TEN99("1099"),

  @SerializedName("w2")
  W2("w2"),

  @SerializedName("personal")
  PERSONAL("personal");

  private final String name;

  IncomeType(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
