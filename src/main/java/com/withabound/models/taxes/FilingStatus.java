package com.withabound.models.taxes;

import com.google.gson.annotations.SerializedName;

public enum FilingStatus {
  @SerializedName("single")
  SINGLE,

  @SerializedName("married")
  MARRIED,

  @SerializedName("marriedSeparately")
  MARRIED_SEPARATELY,

  @SerializedName("headOfHousehold")
  HEAD_OF_HOUSEHOLD,

  @SerializedName("qualifiedWidow")
  QUALIFIED_WIDOW,
}
