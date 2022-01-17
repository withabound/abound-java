package com.withabound.models.documents.w9;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class FormW9DocumentRequest extends DocumentRequest {
  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private Integer year;

  /** The U.S. federal tax classification of the person. */
  private W9TaxClassification taxClassification;

  /** The account numbers to list on this W-9. */
  private List<String> accountNumbers;

  /** The timestamp of the certification of this W-9. */
  private Integer certificationTimestamp;

  @Override
  public DocumentType getType() {
    return DocumentType.W9;
  }

  public Optional<String> getPayerId() {
    return Optional.ofNullable(payerId);
  }

  public Integer getYear() {
    return year;
  }

  public W9TaxClassification getTaxClassification() {
    return taxClassification;
  }

  public List<String> getAccountNumbers() {
    return accountNumbers == null ? Collections.emptyList() : accountNumbers;
  }

  public Integer getCertificationTimestamp() {
    return certificationTimestamp;
  }
}
