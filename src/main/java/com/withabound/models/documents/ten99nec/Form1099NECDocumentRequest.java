package com.withabound.models.documents.ten99nec;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.StateTaxInfoWithIncome;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Form1099NECDocumentRequest extends DocumentRequest {
  private String payerId;

  private int year;

  private String accountNumber;

  private double nonemployeeCompensation;

  private Boolean hasDirectSalesOver5000;

  private Double federalIncomeTaxWithheld;

  private List<StateTaxInfoWithIncome> stateTaxInfo;

  @Override
  public DocumentType getType() {
    return DocumentType.TEN99NEC;
  }

  public String getPayerId() {
    return payerId;
  }

  public int getYear() {
    return year;
  }

  public Optional<String> getAccountNumber() {
    return Optional.ofNullable(accountNumber);
  }

  public double getNonemployeeCompensation() {
    return nonemployeeCompensation;
  }

  public Boolean getHasDirectSalesOver5000() {
    return hasDirectSalesOver5000;
  }

  public Double getFederalIncomeTaxWithheld() {
    return federalIncomeTaxWithheld;
  }

  public List<StateTaxInfoWithIncome> getStateTaxInfo() {
    return stateTaxInfo == null ? Collections.emptyList() : stateTaxInfo;
  }
}
