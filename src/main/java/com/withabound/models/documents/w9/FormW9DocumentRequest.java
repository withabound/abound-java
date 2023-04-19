package com.withabound.models.documents.w9;

import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.IDocumentRequest;
import java.util.Optional;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class FormW9DocumentRequest extends FormW9FormFields implements IDocumentRequest {
  private final DocumentType type = DocumentType.W9;

  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private Integer year;

  public DocumentType getType() {
    return type;
  }

  public Optional<String> getPayerId() {
    return Optional.ofNullable(payerId);
  }

  public Integer getYear() {
    return year;
  }
}
