package com.withabound.models.documents;

public abstract class DocumentRequest {
  private final DocumentType type = getType();

  public abstract DocumentType getType();
}
