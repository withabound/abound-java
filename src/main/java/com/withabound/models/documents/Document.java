package com.withabound.models.documents;

import java.util.Optional;

/** See https://docs.withabound.com/reference/documents */
public class Document {
  private String documentId;

  private String documentURL;

  private String documentName;

  private DocumentType type;

  private String year;

  private Long createdTimestamp;

  public Optional<String> getDocumentId() {
    return Optional.ofNullable(documentId);
  }

  public Optional<String> getDocumentURL() {
    return Optional.ofNullable(documentURL);
  }

  public String getDocumentName() {
    return documentName;
  }

  public DocumentType getType() {
    return type;
  }

  public String getYear() {
    return year;
  }

  public Long getCreatedTimestamp() {
    return createdTimestamp;
  }
}
