package com.withabound.models.payment_methods;

import com.google.gson.annotations.SerializedName;

public enum AccountType {
  @SerializedName("business")
  BUSINESS,

  @SerializedName("personal")
  PERSONAL,
}
