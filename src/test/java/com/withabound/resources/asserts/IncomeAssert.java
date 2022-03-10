package com.withabound.resources.asserts;

import com.withabound.models.incomes.Income;
import com.withabound.models.incomes.IncomeDocumentType;
import com.withabound.models.incomes.IncomeType;
import com.withabound.resources.IncomesTest;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class IncomeAssert extends AbstractAssert<IncomeAssert, Income> {
  public static IncomeAssert assertThat(final Income actual) {
    return new IncomeAssert(actual);
  }

  public IncomeAssert isDesignServicesClientInvoice() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getIncomeId()).isEqualTo(IncomesTest.TEST_INCOME_ID);
    Assertions.assertThat(actual.getIncomeType()).isEqualTo(Optional.of(IncomeType.TEN99));
    Assertions.assertThat(actual.getAmount()).isEqualTo(222.34);
    Assertions.assertThat(actual.getDate()).isEqualTo("2020-01-14");
    Assertions.assertThat(actual.getDescription()).isEqualTo("Client Invoice");
    Assertions.assertThat(actual.getCategory()).isEqualTo(Optional.of("Design Services"));
    Assertions.assertThat(actual.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));

    return this;
  }

  public IncomeAssert isDesignServicesSSA1099ClientInvoice() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getIncomeId()).isEqualTo(IncomesTest.TEST_INCOME_ID);
    Assertions.assertThat(actual.getIncomeType()).isEqualTo(Optional.of(IncomeType.TEN99));
    Assertions.assertThat(actual.getAmount()).isEqualTo(333.34);
    Assertions.assertThat(actual.getDate()).isEqualTo("2020-01-14");
    Assertions.assertThat(actual.getDocumentType())
        .isEqualTo(Optional.of(IncomeDocumentType.SSA1099));
    Assertions.assertThat(actual.getDescription()).isEqualTo("Client Invoice");
    Assertions.assertThat(actual.getCategory()).isEqualTo(Optional.of("Design Services"));
    Assertions.assertThat(actual.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));

    return this;
  }

  private IncomeAssert(final Income actual) {
    super(actual, IncomeAssert.class);
  }
}
