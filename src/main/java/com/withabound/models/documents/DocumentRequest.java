package com.withabound.models.documents;

import lombok.Getter;

@Getter
public abstract class DocumentRequest {
  private final DocumentType type = getType();

  public abstract DocumentType getType();
}
