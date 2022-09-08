package com.withabound.models.taxes;

/** See https://docs.withabound.com/reference/taxes */
public class Tax extends TaxRequest {
  private Double effectiveTaxRate;

  private Double federalIncomeTax;

  private Double federalTaxOutstanding;

  private Double federalTaxTotal;

  private Long irsPayments;

  private Long quarterlyPayments;

  private Double marginalTaxRate;

  private Double medicareTax;

  private Double mileageDeduction;

  private Double qbiDeduction;

  private Double selfEmploymentTax;

  private Double smartTaxRate;

  private Double socialSecurityTax;

  private Double stateIncomeTax;

  private Double stateTaxTotal;

  private Double taxBalance;

  private Double taxWithholdings;

  private Long taxWithholdingsPending;

  private Double totalTax;

  private String year;

  public Tax(
      final Double ten99Income,
      final Double w2Income,
      final Double expenseDeduction,
      final Double mileage,
      final String filingState,
      final FilingStatus filingStatus) {
    super(ten99Income, w2Income, expenseDeduction, mileage, filingState, filingStatus);
  }

  public Double getEffectiveTaxRate() {
    return effectiveTaxRate;
  }

  public Double getFederalIncomeTax() {
    return federalIncomeTax;
  }

  public Double getFederalTaxOutstanding() {
    return federalTaxOutstanding;
  }

  public Double getFederalTaxTotal() {
    return federalTaxTotal;
  }

  public Long getIrsPayments() {
    return irsPayments;
  }

  public Long getQuarterlyPayments() {
    return quarterlyPayments;
  }

  public Double getMarginalTaxRate() {
    return marginalTaxRate;
  }

  public Double getMedicareTax() {
    return medicareTax;
  }

  public Double getMileageDeduction() {
    return mileageDeduction;
  }

  public Double getQbiDeduction() {
    return qbiDeduction;
  }

  public Double getSelfEmploymentTax() {
    return selfEmploymentTax;
  }

  public Double getSmartTaxRate() {
    return smartTaxRate;
  }

  public Double getSocialSecurityTax() {
    return socialSecurityTax;
  }

  public Double getStateIncomeTax() {
    return stateIncomeTax;
  }

  public Double getStateTaxTotal() {
    return stateTaxTotal;
  }

  public Double getTaxBalance() {
    return taxBalance;
  }

  public Double getTaxWithholdings() {
    return taxWithholdings;
  }

  public Long getTaxWithholdingsPending() {
    return taxWithholdingsPending;
  }

  public Double getTotalTax() {
    return totalTax;
  }

  public String getYear() {
    return year;
  }
}
