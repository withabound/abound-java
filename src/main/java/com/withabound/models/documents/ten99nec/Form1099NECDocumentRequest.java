package com.withabound.models.documents.ten99nec;

import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.IDocumentRequest;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class Form1099NECDocumentRequest extends Form1099NECFormFields implements IDocumentRequest {
  private final DocumentType type = DocumentType.TEN99NEC;

  private String payerId;

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
