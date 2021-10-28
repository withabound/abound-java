package com.withabound.models.tax_payments;

public class TaxPaymentDocument {
  private String documentURL;

  private String documentName;

  private TaxPaymentDocumentType type;

  private String year;

  public String getDocumentURL() {
    return documentURL;
  }

  public String getDocumentName() {
    return documentName;
  }

  public TaxPaymentDocumentType getType() {
    return type;
  }

  public String getYear() {
    return year;
  }
}
