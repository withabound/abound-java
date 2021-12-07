package com.withabound.models.documents.ten99k;

import lombok.Builder;
import lombok.Setter;

/** An object that contains the gross amount of transactions for each month. */
@Builder
@Setter
public class GrossAmountsByMonth {
  private Double january;

  private Double february;

  private Double march;

  private Double april;

  private Double may;

  private Double june;

  private Double july;

  private Double august;

  private Double september;

  private Double october;

  private Double november;

  private Double december;

  public Double getJanuary() {
    return january;
  }

  public Double getFebruary() {
    return february;
  }

  public Double getMarch() {
    return march;
  }

  public Double getApril() {
    return april;
  }

  public Double getMay() {
    return may;
  }

  public Double getJune() {
    return june;
  }

  public Double getJuly() {
    return july;
  }

  public Double getAugust() {
    return august;
  }

  public Double getSeptember() {
    return september;
  }

  public Double getOctober() {
    return october;
  }

  public Double getNovember() {
    return november;
  }

  public Double getDecember() {
    return december;
  }
}
