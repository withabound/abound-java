package com.withabound.resources.asserts;

import com.withabound.models.mailings.Mailing;
import com.withabound.models.mailings.MailingAddress;
import com.withabound.resources.MailingsTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class MailingAssert extends AbstractAssert<MailingAssert, Mailing> {
  public static MailingAssert assertThat(final Mailing actual) {
    return new MailingAssert(actual);
  }

  public MailingAssert isValidMailing() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getMailingId()).isEqualTo(MailingsTest.TEST_MAILING_ID);

    final MailingAddress to = actual.getTo();
    Assertions.assertThat(to).isNotNull();
    Assertions.assertThat(to.getName().orElse(null)).isEqualTo("Ada Lovelace");
    Assertions.assertThat(to.getCompany().orElse(null)).isEqualTo("InGen Corporation");
    Assertions.assertThat(to.getAddress()).isEqualTo("256 Byron Street");
    Assertions.assertThat(to.getAddress2().orElse(null)).isEqualTo("Suite 32");
    Assertions.assertThat(to.getCity()).isEqualTo("Palo Alto");
    Assertions.assertThat(to.getState()).isEqualTo("CA");
    Assertions.assertThat(to.getZipcode()).isEqualTo("94306");
    Assertions.assertThat(to.getCountry().orElse(null)).isEqualTo("US");

    final MailingAddress from = actual.getFrom();
    Assertions.assertThat(from).isNotNull();
    Assertions.assertThat(from.getCompany().orElse(null)).isEqualTo("Hooli");
    Assertions.assertThat(from.getAddress()).isEqualTo("1401 N Shoreline Blvd");
    Assertions.assertThat(from.getAddress2().orElse(null)).isEqualTo("Suite 1");
    Assertions.assertThat(from.getCity()).isEqualTo("Mountain View");
    Assertions.assertThat(from.getState()).isEqualTo("CA");
    Assertions.assertThat(from.getZipcode()).isEqualTo("94043");
    Assertions.assertThat(from.getCountry().orElse(null)).isEqualTo("US");

    Assertions.assertThat(actual.getStatus()).isEqualTo("created");
    Assertions.assertThat(actual.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));

    return this;
  }

  private MailingAssert(final Mailing actual) {
    super(actual, MailingAssert.class);
  }
}
