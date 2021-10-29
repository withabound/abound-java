package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.expenses.Expense;
import com.withabound.models.expenses.ExpenseRequest;
import com.withabound.models.expenses.ExpenseType;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.ExpenseAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpensesTest extends AbstractAboundTest {
  public static final String TEST_EXPENSE_ID = "expenseId_testB1FBA298F0154D1906F18AF8C8D97FDCBD28";

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Expense> response =
        getAboundClient().expenses().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    assertThat(response.getData()).isNotNull().hasSize(1);

    ExpenseAssert.assertThat(response.getData().get(0)).isPenAndPaperExpense();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Expense> response =
        getAboundClient().expenses().retrieve(TestUtils.TEST_USER_ID, TEST_EXPENSE_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    ExpenseAssert.assertThat(response.getData()).isPenAndPaperExpense();
  }

  @Test
  public void testCreate() throws IOException {
    final Double randAmount = TestUtils.randomCurrencyAmount();
    final String randDescription = TestUtils.randomAlphabetic();

    final ExpenseRequest randomExpense =
        ExpenseRequest.builder()
            .amount(randAmount)
            .description(randDescription)
            .expenseType(ExpenseType.PERSONAL)
            .date("2021-02-13")
            .build();

    final ExpenseRequest tires =
        ExpenseRequest.builder().amount(600.88).date("2020-12-12").description("tires").build();

    final AboundBulkResponse<Expense> response =
        getAboundClient()
            .expenses()
            .create(TestUtils.TEST_USER_ID, Arrays.asList(randomExpense, tires));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    assertThat(response.getData()).hasSize(2);

    final Expense first = response.getData().get(0);

    assertThat(first).isNotNull();
    assertThat(first.getAmount().orElse(null)).isEqualTo(randAmount);
    assertThat(first.getDescription().orElse(null)).isEqualTo(randDescription);
    assertThat(first.getExpenseType().orElse(null)).isEqualTo(ExpenseType.PERSONAL);
    assertThat(first.getDate().orElse(null)).isEqualTo("2021-02-13");

    final Expense second = response.getData().get(1);
    ExpenseAssert.assertThat(second).hasExpensePredictions();

    assertThat(second).isNotNull();
    assertThat(second.getAmount().orElse(null)).isEqualTo(600.88);
    assertThat(second.getDeductionAmount()).isEqualTo(300.44);
    assertThat(second.getDescription().orElse(null)).isEqualTo("tires");
    assertThat(second.getExpenseType().orElse(null)).isEqualTo(ExpenseType.BUSINESS);
    assertThat(second.getTaxCategory().orElse(null)).isEqualTo("Meals");
    assertThat(second.getDate().orElse(null)).isEqualTo("2020-12-12");
  }

  @Test
  public void testUpdate() throws IOException {
    final AboundResponse<Expense> response =
        getAboundClient()
            .expenses()
            .update(
                TestUtils.TEST_USER_ID,
                TEST_EXPENSE_ID,
                ExpenseRequest.builder().description("something new!").build());

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    final Expense updated = response.getData();
    assertThat(updated.getDescription().orElse(null)).isEqualTo("something new!");

    Assertions.assertThat(updated.getExpenseId()).isEqualTo(ExpensesTest.TEST_EXPENSE_ID);
    Assertions.assertThat(updated.getTaxCategory().orElse(null)).isEqualTo("Meals");
    Assertions.assertThat(updated.getAmount().orElse(null)).isEqualTo(123.54);
    Assertions.assertThat(updated.getDeductionAmount()).isEqualTo(61.77);
    Assertions.assertThat(updated.getDate().orElse(null)).isEqualTo("2020-05-12");
    Assertions.assertThat(updated.getForeignId()).isEmpty();
    Assertions.assertThat(updated.getExpenseType().orElse(null)).isEqualTo(ExpenseType.BUSINESS);
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().expenses().delete(TestUtils.TEST_USER_ID, TEST_EXPENSE_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata().hasEmptyDataObject();
  }
}
