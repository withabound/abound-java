package com.withabound.models.incomes;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.IncomeAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class IncomesTest extends AbstractAboundTest {
  public static final String TEST_INCOME_ID = "incomeId_test8cb0d56b942722b6d719fa5aa9c5a8dbaa0f";

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Income> response =
        getAboundClient().incomes().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Income> listed = response.getData();
    assertThat(listed).hasSize(1);

    final Income income = listed.get(0);
    IncomeAssert.assertThat(income).isDesignServicesClientInvoice();
  }

  @Test
  public void testCreate() throws IOException {
    final IncomeRequest ten99 =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99)
            .amount(123.45)
            .date("2020-01-01")
            .category("1099 income")
            .description("1099 description")
            .foreignId("1099_foreign_id")
            .build();

    final IncomeRequest ten99Int =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99INT)
            .amount(444.77)
            .date("2020-02-01")
            .category("1099-INT income")
            .description("1099-INT description")
            .foreignId("1099int_foreign_id")
            .build();

    final IncomeRequest w2 =
        IncomeRequest.builder()
            .incomeType(IncomeType.W2)
            .amount(32000.99)
            .date("2020-03-01")
            .category("W2 income")
            .description("W2 description")
            .foreignId("w2_foreign_id")
            .build();

    final IncomeRequest personal =
        IncomeRequest.builder()
            .incomeType(IncomeType.PERSONAL)
            .amount(999.10)
            .date("2020-04-01")
            .category("Personal income")
            .description("Personal description")
            .foreignId("personal_foreign_id")
            .build();

    final AboundBulkResponse<Income> response =
        getAboundClient()
            .incomes()
            .create(TestUtils.TEST_USER_ID, Arrays.asList(ten99, ten99Int, w2, personal));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Income> created = response.getData();
    assertThat(created).hasSize(4);

    final Income createdTen99 = created.get(0);
    assertThat(createdTen99).isNotNull();
    assertThat(createdTen99.getIncomeId()).isNotEmpty();
    assertThat(createdTen99.getIncomeType()).isEqualTo(IncomeType.TEN99);
    assertThat(createdTen99.getAmount()).isEqualTo(ten99.getAmount());
    assertThat(createdTen99.getDate()).isEqualTo(ten99.getDate());
    assertThat(createdTen99.getDescription()).isEqualTo(ten99.getDescription());
    assertThat(createdTen99.getCategory()).isEqualTo(ten99.getCategory());
    assertThat(createdTen99.getForeignId()).isEqualTo(ten99.getForeignId());

    final Income createdTen99Int = created.get(1);
    assertThat(createdTen99Int).isNotNull();
    assertThat(createdTen99Int.getIncomeId()).isNotEmpty();
    // the commented assertions below are due to the nature of some Incomes created with test
    // credentials returning as 1099, despite being created with a different incomeType. This issue
    // only exists with the test credentials.

    // assertThat(createdTen99Int.getIncomeType()).isEqualTo(IncomeType.TEN99INT);
    assertThat(createdTen99Int.getAmount()).isEqualTo(ten99Int.getAmount());
    assertThat(createdTen99Int.getDate()).isEqualTo(ten99Int.getDate());
    assertThat(createdTen99Int.getDescription()).isEqualTo(ten99Int.getDescription());
    assertThat(createdTen99Int.getCategory()).isEqualTo(ten99Int.getCategory());
    assertThat(createdTen99Int.getForeignId()).isEqualTo(ten99Int.getForeignId());

    final Income createdW2 = created.get(2);
    assertThat(createdW2).isNotNull();
    assertThat(createdW2.getIncomeId()).isNotEmpty();
    // assertThat(createdW2.getIncomeType()).isEqualTo(IncomeType.W2);
    assertThat(createdW2.getAmount()).isEqualTo(w2.getAmount());
    assertThat(createdW2.getDate()).isEqualTo(w2.getDate());
    assertThat(createdW2.getDescription()).isEqualTo(w2.getDescription());
    assertThat(createdW2.getCategory()).isEqualTo(w2.getCategory());
    assertThat(createdW2.getForeignId()).isEqualTo(w2.getForeignId());

    final Income createdPersonal = created.get(3);
    assertThat(createdPersonal).isNotNull();
    assertThat(createdPersonal.getIncomeId()).isNotEmpty();
    assertThat(createdPersonal.getIncomeType()).isEqualTo(IncomeType.PERSONAL);
    assertThat(createdPersonal.getAmount()).isEqualTo(personal.getAmount());
    assertThat(createdPersonal.getDate()).isEqualTo(personal.getDate());
    assertThat(createdPersonal.getDescription()).isEqualTo(personal.getDescription());
    assertThat(createdPersonal.getCategory()).isEqualTo(personal.getCategory());
    assertThat(createdPersonal.getForeignId()).isEqualTo(personal.getForeignId());
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Income> response =
        getAboundClient().incomes().retrieve(TestUtils.TEST_USER_ID, TEST_INCOME_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Income retrieved = response.getData();

    IncomeAssert.assertThat(retrieved).isDesignServicesClientInvoice();
  }

  @Test
  public void testUpdate() throws IOException {
    final String description = TestUtils.randomAlphabetic();
    final String date = TestUtils.randomDate();
    final String category = TestUtils.randomAlphabetic();
    final Double amount = TestUtils.randomCurrencyAmount();

    final IncomeRequest toUpdate =
        IncomeRequest.builder()
            .description(description)
            .date(date)
            .category(category)
            .amount(amount)
            .build();

    final AboundResponse<Income> response =
        getAboundClient().incomes().update(TestUtils.TEST_USER_ID, TEST_INCOME_ID, toUpdate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Income updated = response.getData();

    assertThat(updated).isNotNull();
    assertThat(updated.getIncomeId()).isEqualTo(TEST_INCOME_ID);
    assertThat(updated.getDescription()).isEqualTo(Optional.of(description));
    assertThat(updated.getDate()).isEqualTo(date);
    assertThat(updated.getCategory()).isEqualTo(Optional.of(category));
    assertThat(updated.getAmount()).isEqualTo(amount);
    assertThat(updated.getIncomeType()).isEqualTo(IncomeType.TEN99);
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().incomes().delete(TestUtils.TEST_USER_ID, TEST_INCOME_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata().hasEmptyDataObject();
  }
}
