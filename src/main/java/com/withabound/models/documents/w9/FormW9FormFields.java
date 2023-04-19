package com.withabound.models.documents.w9;

import java.util.Collections;
import java.util.List;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class FormW9FormFields {
  /** The U.S. federal tax classification of the person. */
  private W9TaxClassification taxClassification;

  /** The code to identify a payee that is exempt from backup withholding. */
  private ExemptPayeeCode exemptPayeeCode;

  /** The code to identify a payee that is exempt from reporting under FATCA. */
  private ExemptFatcaCode exemptFatcaCode;

  /** The account numbers to list on this W-9. */
  private List<String> accountNumbers;

  /** The timestamp of the certification of this W-9. */
  private Integer certificationTimestamp;

  public W9TaxClassification getTaxClassification() {
    return taxClassification;
  }

  public ExemptPayeeCode getExemptPayeeCode() {
    return exemptPayeeCode;
  }

  public ExemptFatcaCode getExemptFatcaCode() {
    return exemptFatcaCode;
  }

  public List<String> getAccountNumbers() {
    return accountNumbers == null ? Collections.emptyList() : accountNumbers;
  }

  public Integer getCertificationTimestamp() {
    return certificationTimestamp;
  }
}
