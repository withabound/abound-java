package com.withabound.models.expenses;

import com.google.gson.annotations.SerializedName;

public enum ExpenseType {
  @SerializedName("business")
  BUSINESS,

  @SerializedName("personal")
  PERSONAL,
}
