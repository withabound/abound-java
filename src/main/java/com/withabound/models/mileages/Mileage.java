package com.withabound.models.mileages;

import lombok.Getter;

@Getter
public class Mileage extends MileageRequest {
  private String transactionId;

  public Mileage(final Double distance, final String date, final String description) {
    super(distance, date, description);
  }
}
