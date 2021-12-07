package com.withabound.models.documents.w9;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class FormW9DocumentRequest extends DocumentRequest {
  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private int year;

  /** The U.S. federal tax classification of the person. */
  private W9TaxClassification taxClassification;

  /** The account numbers to list on this W-9. */
  private List<String> accountNumbers;

  /** The timestamp of the certification of this W-9. */
  private int certificationTimestamp;

  @Override
  public DocumentType getType() {
    return DocumentType.W9;
  }

  public String getPayerId() {
    return payerId;
  }

  public int getYear() {
    return year;
  }

  public W9TaxClassification getTaxClassification() {
    return taxClassification;
  }

  public List<String> getAccountNumbers() {
    return accountNumbers == null ? Collections.emptyList() : accountNumbers;
  }

  public int getCertificationTimestamp() {
    return certificationTimestamp;
  }
}
