package com.withabound.models.documents.ten99nec;

import com.withabound.models.documents.StateTaxInfoWithIncome;
import java.util.List;
import java.util.Optional;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class Form1099NECFormFields {
  private Boolean isCorrected;

  private String accountNumber;

  private Double nonemployeeCompensation;

  private Boolean hasDirectSalesOver5000;

  private Double federalIncomeTaxWithheld;

  private List<StateTaxInfoWithIncome> stateTaxInfo;

  public Optional<Boolean> getIsCorrected() {
    return Optional.ofNullable(isCorrected);
  }

  public Optional<String> getAccountNumber() {
    return Optional.ofNullable(accountNumber);
  }

  public Double getNonemployeeCompensation() {
    return nonemployeeCompensation;
  }

  public Boolean getHasDirectSalesOver5000() {
    return hasDirectSalesOver5000;
  }

  public Double getFederalIncomeTaxWithheld() {
    return federalIncomeTaxWithheld;
  }

  public List<StateTaxInfoWithIncome> getStateTaxInfo() {
    return stateTaxInfo;
  }
}
