package com.withabound.models.documents;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class StateTaxInfo {
  /** Lowercase, two-letter code */
  private String filingState;

  /** The payer's state identification number. */
  private String stateId;

  /** The state tax withheld. */
  private Double stateTaxWithheld;

  public String getFilingState() {
    return filingState;
  }

  public String getStateId() {
    return stateId;
  }

  public Double getStateTaxWithheld() {
    return stateTaxWithheld;
  }
}
