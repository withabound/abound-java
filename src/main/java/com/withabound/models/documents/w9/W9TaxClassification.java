package com.withabound.models.documents.w9;

import com.google.gson.annotations.SerializedName;

public enum W9TaxClassification {
  @SerializedName("individual")
  INDIVIDUAL,

  @SerializedName("soleProprietor")
  SOLE_PROPRIETOR,

  @SerializedName("singleMemberLlc")
  SINGLE_MEMBER_LLC,

  @SerializedName("cCorporation")
  C_CORPORATION,

  @SerializedName("sCorporation")
  S_CORPORATION,

  @SerializedName("partnership")
  PARTNERSHIP,

  @SerializedName("trust")
  TRUST,

  @SerializedName("estate")
  ESTATE,

  @SerializedName("llcCCorporation")
  LLC_C_CORPORATION,

  @SerializedName("llcSCorporation")
  LLC_S_CORPORATION,

  @SerializedName("llcPartnership")
  LLC_PARTNERSHIP,
}
