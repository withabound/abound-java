package com.withabound.models.payment_methods;

import com.google.gson.annotations.SerializedName;

public enum AccountClass {
  @SerializedName("checking")
  CHECKING,

  @SerializedName("savings")
  SAVINGS,
}
