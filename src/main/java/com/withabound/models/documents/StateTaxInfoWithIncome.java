package com.withabound.models.documents;

import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class StateTaxInfoWithIncome extends StateTaxInfo {
  private Double stateIncome;

  public Double getStateIncome() {
    return stateIncome;
  }
}
