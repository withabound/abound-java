package com.withabound.resources.asserts;

import com.withabound.models.payment_methods.AccountClass;
import com.withabound.models.payment_methods.AccountType;
import com.withabound.models.payment_methods.PaymentMethod;
import com.withabound.resources.PaymentMethodsTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class PaymentMethodAssert extends AbstractAssert<PaymentMethodAssert, PaymentMethod> {
  public static PaymentMethodAssert assertThat(final PaymentMethod actual) {
    return new PaymentMethodAssert(actual);
  }

  public PaymentMethodAssert isUnverified7890PersonalCheckingAccount() {
    Assertions.assertThat(actual.getPaymentMethodId())
        .isEqualTo(PaymentMethodsTest.TEST_PAYMENT_METHOD_ID);
    Assertions.assertThat(actual.getDisplayName().orElse(null))
        .isEqualTo("(7890) personal checking");
    Assertions.assertThat(actual.getAccountNumberLast4().orElse(null)).isEqualTo("7890");
    Assertions.assertThat(actual.getStatus()).isEqualTo("unverified");
    Assertions.assertThat(actual.getAccountNumber()).isNull();
    Assertions.assertThat(actual.getRoutingNumber()).isNull();
    Assertions.assertThat(actual.getAccountType()).isEqualTo(AccountType.PERSONAL);
    Assertions.assertThat(actual.getAccountClass()).isEqualTo(AccountClass.CHECKING);

    return this;
  }

  private PaymentMethodAssert(final PaymentMethod actual) {
    super(actual, PaymentMethodAssert.class);
  }
}
