package com.withabound.resources.asserts;

import com.withabound.models.tax_payments.TaxPayment;
import com.withabound.models.tax_payments.TaxPaymentDocument;
import com.withabound.models.tax_payments.TaxPaymentDocumentType;
import com.withabound.models.tax_payments.TaxPeriod;
import com.withabound.resources.PaymentMethodsTest;
import com.withabound.resources.TaxPaymentsTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TaxPaymentAssert extends AbstractAssert<TaxPaymentAssert, TaxPayment> {
  public static TaxPaymentAssert assertThat(final TaxPayment actual) {
    return new TaxPaymentAssert(actual);
  }

  public TaxPaymentAssert is2020Q2FederalEstimatedTaxPayment(
      final boolean isFullyExpanded, final String status) {
    Assertions.assertThat(actual.getTaxPaymentId()).isEqualTo(TaxPaymentsTest.TEST_TAX_PAYMENT_ID);
    Assertions.assertThat(actual.getYear()).isEqualTo("2020");
    Assertions.assertThat(actual.getPeriod()).isEqualTo(TaxPeriod.Q2);
    Assertions.assertThat(actual.getStatus()).isEqualTo(status);
    Assertions.assertThat(actual.getAmount()).isEqualTo(154.66);
    Assertions.assertThat(actual.getPaymentMethodId())
        .isEqualTo(PaymentMethodsTest.TEST_PAYMENT_METHOD_ID);
    Assertions.assertThat(actual.getCreatedDate()).isNotEmpty();

    final TaxPaymentDocument document = actual.getDocument().orElse(null);

    if (isFullyExpanded) {
      Assertions.assertThat(document).isNotNull();
      Assertions.assertThat(document.getDocumentURL())
          .startsWith("https://tax-documents-sandbox.s3.us-west-2.amazonaws.com");
      Assertions.assertThat(document.getDocumentName())
          .isEqualTo("2020 Q2 IRS Estimated Tax Payment");
      Assertions.assertThat(document.getType()).isEqualTo(TaxPaymentDocumentType.TEN40ES);
      Assertions.assertThat(document.getYear()).isEqualTo("2020");
    } else {
      Assertions.assertThat(document).isNull();
    }

    return this;
  }

  private TaxPaymentAssert(final TaxPayment actual) {
    super(actual, TaxPaymentAssert.class);
  }
}
