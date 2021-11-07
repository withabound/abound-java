package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.taxes.FilingStatus;
import com.withabound.models.taxes.Tax;
import com.withabound.models.taxes.TaxRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.TaxAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TaxesTest extends AbstractAboundTest {
  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Tax> response = getAboundClient().taxes().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Tax> taxes = response.getData();
    assertThat(taxes).hasSize(1);

    TaxAssert.assertThat(taxes.get(0)).isTestUser2020Taxes();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Tax> response =
        getAboundClient().taxes().retrieve(TestUtils.TEST_USER_ID, "2020");

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Tax tax = response.getData();

    TaxAssert.assertThat(tax).isTestUser2020Taxes();
  }

  @Test
  public void testCalculate() throws IOException {
    final TaxRequest toUpdate =
        TaxRequest.builder()
            .filingState("fl")
            .filingStatus(FilingStatus.MARRIED)
            .w2Income(75000.0)
            .mileage(16500.0)
            .build();

    final AboundResponse<Tax> response =
        getAboundClient().taxes().calculate(TestUtils.TEST_USER_ID, "2020", toUpdate);

    final Tax calculated = response.getData();

    TaxAssert.assertThat(calculated).isTestUser2020Taxes();
  }
}
