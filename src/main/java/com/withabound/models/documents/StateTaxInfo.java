package com.withabound.models.documents;

import java.util.Optional;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class StateTaxInfo {
  private Double stateTaxWithheld;

  private String filingState;

  private String payerStateId;

  public Double getStateTaxWithheld() {
    return stateTaxWithheld;
  }

  public String getFilingState() {
    return filingState;
  }

  public Optional<String> getPayerStateId() {
    return Optional.ofNullable(payerStateId);
  }
}
