package com.withabound.models.documents.ten99_k;

import com.google.gson.annotations.SerializedName;

public enum PayerClassification {
  @SerializedName("pse")
  PAYMENT_SETTLEMENT_ENTITY,

  @SerializedName("epfOther")
  ELECTRONIC_PAYMENT_FACILITATOR_OR_OTHER_THIRD_PARTY,
}
