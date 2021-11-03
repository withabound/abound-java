package com.withabound.models.incomes;

import com.google.gson.annotations.SerializedName;

public enum IncomeType {
  @SerializedName("1099")
  TEN99,

  @SerializedName("1099-INT")
  TEN99INT,

  @SerializedName("w2")
  W2,

  @SerializedName("personal")
  PERSONAL,
}
