package com.withabound.models.mileages;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class MileageRequest {
  private Double distance;

  private String date; // YYYY-MM-DD

  private String description;

  public Double getDistance() {
    return distance;
  }

  public String getDate() {
    return date;
  }

  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }
}
