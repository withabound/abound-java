package com.withabound.models.documents;

import com.google.gson.annotations.SerializedName;

public enum DocumentType {
  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  @SerializedName("accountStatement")
  ACCOUNT_STATEMENT,

  @SerializedName("1099int")
  TEN99INT,

  @SerializedName("1099k")
  TEN99K,

  // @SerializedName("1099misc")
  // TEN99MISC,

  @SerializedName("1099nec")
  TEN99NEC,

  @SerializedName("w9")
  W9,
}
