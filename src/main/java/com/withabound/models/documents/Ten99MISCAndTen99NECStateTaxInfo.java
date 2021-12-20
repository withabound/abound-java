package com.withabound.models.documents;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Ten99MISCAndTen99NECStateTaxInfo {
  private double stateTaxWithheld;

  private String filingState;

  private String payersStateNumber;

  private double stateIncome;

  public double getStateTaxWithheld() {
    return stateTaxWithheld;
  }

  public String getFilingState() {
    return filingState;
  }

  public String getPayersStateNumber() {
    return payersStateNumber;
  }

  public double getStateIncome() {
    return stateIncome;
  }
}
