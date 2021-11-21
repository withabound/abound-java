package com.withabound.models.documents.ten99_k;

import com.google.gson.annotations.SerializedName;

public enum TransactionsReportedClassification {
  @SerializedName("paymentCard")
  PAYMENT_CARD,

  @SerializedName("thirdPartyNetwork")
  THIRD_PARTY_NETWORK,
}
