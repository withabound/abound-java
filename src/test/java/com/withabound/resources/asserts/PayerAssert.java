package com.withabound.resources.asserts;

import com.withabound.models.payers.Payer;
import com.withabound.resources.PayersTest;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class PayerAssert extends AbstractAssert<PayerAssert, Payer> {
  public static PayerAssert assertThat(final Payer actual) {
    return new PayerAssert(actual);
  }

  public PayerAssert isHooli() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getPayerId()).isEqualTo(PayersTest.TEST_PAYER_ID);
    Assertions.assertThat(actual.getName()).isEqualTo("Hooli");
    Assertions.assertThat(actual.getAddress()).isEqualTo("1401 N Shoreline Blvd");
    Assertions.assertThat(actual.getAddress2()).isEqualTo(Optional.of("Suite 1"));
    Assertions.assertThat(actual.getCity()).isEqualTo("Mountain View");
    Assertions.assertThat(actual.getState()).isEqualTo("CA");
    Assertions.assertThat(actual.getCountry()).isEqualTo("US");
    Assertions.assertThat(actual.getZipcode()).isEqualTo("94043");
    Assertions.assertThat(actual.getPhoneNumber()).isEqualTo("6501014096");
    Assertions.assertThat(actual.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));

    return this;
  }

  private PayerAssert(final Payer actual) {
    super(actual, PayerAssert.class);
  }
}
