package com.withabound.models.documents.ten99k;

import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.IDocumentRequest;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class Form1099KDocumentRequest extends Form1099KFormFields implements IDocumentRequest {
  private final DocumentType type = DocumentType.TEN99K;

  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private Integer year;

  public DocumentType getType() {
    return type;
  }

  public String getPayerId() {
    return payerId;
  }

  public Integer getYear() {
    return year;
  }
}
