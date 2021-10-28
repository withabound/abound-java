package com.withabound.resources.asserts;

import com.withabound.models.expenses.Expense;
import com.withabound.models.expenses.ExpenseType;
import com.withabound.resources.ExpensesTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ExpenseAssert extends AbstractAssert<ExpenseAssert, Expense> {
  public static ExpenseAssert assertThat(final Expense actual) {
    return new ExpenseAssert(actual);
  }

  public ExpenseAssert isPenAndPaperExpense() {
    Assertions.assertThat(actual).isNotNull();

    Assertions.assertThat(actual.getExpenseId()).isEqualTo(ExpensesTest.TEST_EXPENSE_ID);
    Assertions.assertThat(actual.getTaxCategory().orElse(null)).isEqualTo("Meals");
    Assertions.assertThat(actual.getAmount().orElse(null)).isEqualTo(123.54);
    Assertions.assertThat(actual.getDescription().orElse(null)).isEqualTo("Pen and paper");
    Assertions.assertThat(actual.getDeductionAmount()).isEqualTo(61.77);
    Assertions.assertThat(actual.getDate().orElse(null)).isEqualTo("2020-05-12");
    Assertions.assertThat(actual.getForeignId()).isEmpty();
    Assertions.assertThat(actual.getExpenseType().orElse(null)).isEqualTo(ExpenseType.BUSINESS);

    hasExpensePredictions();

    return this;
  }

  public ExpenseAssert hasExpensePredictions() {
    Assertions.assertThat(actual.getPredictions()).isNotNull();
    Assertions.assertThat(actual.getPredictions().getExpenseTypePredictionScores())
        .isNotNull()
        .hasSize(2);
    Assertions.assertThat(actual.getPredictions().getTaxCategoryPredictionScores())
        .isNotNull()
        .hasSize(23);

    return this;
  }

  private ExpenseAssert(final Expense actual) {
    super(actual, ExpenseAssert.class);
  }
}
