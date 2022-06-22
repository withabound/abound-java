package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.tax_payments.TaxPayment;
import com.withabound.models.tax_payments.TaxPaymentEntity;
import com.withabound.models.tax_payments.TaxPaymentParams;
import com.withabound.models.tax_payments.TaxPaymentRequest;
import com.withabound.models.tax_payments.TaxPeriod;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.TaxPaymentAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

public class TaxPaymentsTest extends AbstractAboundTest {
  public static final String TEST_TAX_PAYMENT_ID =
      "taxPaymentId_test614d255d3048f6f7b3b5bb219b18f0f065d3";

  @Test
  public void testCreate() throws IOException {
    final String currentYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    final TaxPaymentRequest toCreate =
        TaxPaymentRequest.builder()
            .year(currentYear)
            .period(TaxPeriod.Q1)
            .amount(333.99)
            .entity(TaxPaymentEntity.IRS)
            .paymentMethodId(PaymentMethodsTest.TEST_PAYMENT_METHOD_ID)
            .build();

    final AboundResponse<TaxPayment> response =
        getAboundClient().taxPayments().create(TestUtils.TEST_USER_ID, toCreate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final TaxPayment created = response.getData();

    assertThat(created).isNotNull();
    assertThat(created.getYear()).isEqualTo(currentYear);
    assertThat(created.getAmount()).isEqualTo(333.99);
    assertThat(created.getEntity()).isEqualTo(TaxPaymentEntity.IRS);
    assertThat(created.getPaymentMethodId()).isEqualTo(PaymentMethodsTest.TEST_PAYMENT_METHOD_ID);
    assertThat(created.getDocument()).isEmpty();
    assertThat(created.getStatus()).isEqualTo("created");
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<TaxPayment> response =
        getAboundClient().taxPayments().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<TaxPayment> retrieved = response.getData();

    assertThat(retrieved).hasSize(1);

    TaxPaymentAssert.assertThat(retrieved.get(0))
        .is2020Q2FederalEstimatedTaxPayment(false, "created");
  }

  @Test
  public void testListWithManyParams() throws IOException, InterruptedException {
    final String foreignId = TestUtils.randomAlphabetic();
    final String nextPage = TestUtils.randomAlphabetic();
    final TaxPaymentParams params =
        TaxPaymentParams.builder().foreignId(foreignId).page(nextPage).build();

    getMockAboundClient().taxPayments().list(TestUtils.TEST_USER_ID, params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
    assertThat(requestUrl.queryParameter("foreignId")).isEqualTo(foreignId);
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<TaxPayment> response =
        getAboundClient().taxPayments().retrieve(TestUtils.TEST_USER_ID, TEST_TAX_PAYMENT_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final TaxPayment retrieved = response.getData();

    TaxPaymentAssert.assertThat(retrieved).is2020Q2FederalEstimatedTaxPayment(true, "done");
  }
}
