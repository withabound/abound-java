package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.payment_methods.AccountClass;
import com.withabound.models.payment_methods.AccountType;
import com.withabound.models.payment_methods.PaymentMethod;
import com.withabound.models.payment_methods.PaymentMethodRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.PaymentMethodAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PaymentMethodsTest extends AbstractAboundTest {
  public static final String TEST_PAYMENT_METHOD_ID =
      "paymentMethodId_test32920837fa800382b7ee5676f281fbfc18cb";

  @Test
  public void testCreate() throws IOException {
    final String accountNumber = TestUtils.randomNumberString(10);
    final String last4 = accountNumber.substring(6);
    final String routingNumber = "102001017";

    final AboundResponse<PaymentMethod> response =
        getAboundClient()
            .paymentMethods()
            .create(
                TestUtils.TEST_USER_ID,
                PaymentMethodRequest.builder()
                    .accountNumber(accountNumber)
                    .routingNumber(routingNumber)
                    .accountClass(AccountClass.CHECKING)
                    .accountType(AccountType.BUSINESS)
                    .build());

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final PaymentMethod created = response.getData();

    assertThat(created.getPaymentMethodId()).isEqualTo(TEST_PAYMENT_METHOD_ID);
    assertThat(created.getAccountNumber()).isNull();
    assertThat(created.getAccountNumberLast4().orElse(null)).isEqualTo(last4);
    assertThat(created.getRoutingNumber()).isNull();
    assertThat(created.getAccountClass()).isEqualTo(AccountClass.CHECKING);
    assertThat(created.getAccountType()).isEqualTo(AccountType.BUSINESS);
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<PaymentMethod> response =
        getAboundClient().paymentMethods().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<PaymentMethod> paymentMethods = response.getData();
    assertThat(paymentMethods).hasSize(1);
    PaymentMethodAssert.assertThat(paymentMethods.get(0)).isUnverified7890PersonalCheckingAccount();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<PaymentMethod> response =
        getAboundClient().paymentMethods().retrieve(TestUtils.TEST_USER_ID, TEST_PAYMENT_METHOD_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    PaymentMethodAssert.assertThat(response.getData()).isUnverified7890PersonalCheckingAccount();
  }
}
