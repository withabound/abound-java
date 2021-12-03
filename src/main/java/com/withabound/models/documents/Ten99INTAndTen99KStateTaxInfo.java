package com.withabound.models.documents;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Ten99INTAndTen99KStateTaxInfo {
  /** The filing state code, lowercase abbreviation (e.g. "ca" for California). */
  private String filingState;

  /** The payer's state identification number. */
  private String stateId;

  /** The state tax withheld. */
  private double stateTaxWithheld;

  public String getFilingState() {
    return filingState;
  }

  public String getStateId() {
    return stateId;
  }

  public double getStateTaxWithheld() {
    return stateTaxWithheld;
  }
}
