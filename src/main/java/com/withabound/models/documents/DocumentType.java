package com.withabound.models.documents;

import com.google.gson.annotations.SerializedName;

public enum DocumentType {
  @SerializedName("accountStatement")
  ACCOUNT_STATEMENT,

  @SerializedName("1099int")
  TEN99INT,

  @SerializedName("1099k")
  TEN99K,

  @SerializedName("1099misc")
  TEN99MISC,

  @SerializedName("1099nec")
  TEN99NEC,

  @SerializedName("w9")
  W9,
}
