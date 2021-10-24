package com.withabound.resources.asserts;

import com.withabound.models.mileages.Mileage;
import com.withabound.resources.MileagesTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class MileageAssert extends AbstractAssert<MileageAssert, Mileage> {
  public static MileageAssert assertThat(final Mileage actual) {
    return new MileageAssert(actual);
  }

  public MileageAssert is09Jan2020OnSiteClientVisit() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getDate()).isEqualTo("2020-01-09");
    Assertions.assertThat(actual.getDescription().orElse(null)).isEqualTo("On-site Client Visit");
    Assertions.assertThat(actual.getDistance()).isEqualTo(23.1);
    Assertions.assertThat(actual.getTransactionId()).isEqualTo(MileagesTest.TEST_MILEAGE_ID);

    return this;
  }

  private MileageAssert(final Mileage actual) {
    super(actual, MileageAssert.class);
  }
}
