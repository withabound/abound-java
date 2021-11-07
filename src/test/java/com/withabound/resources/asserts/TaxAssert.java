package com.withabound.resources.asserts;

import com.withabound.models.taxes.FilingStatus;
import com.withabound.models.taxes.Tax;
import com.withabound.util.TestUtils;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TaxAssert extends AbstractAssert<TaxAssert, Tax> {
  public static TaxAssert assertThat(final Tax actual) {
    return new TaxAssert(actual);
  }

  /** 2020 taxes for {@link TestUtils#TEST_USER_ID} */
  public TaxAssert isTestUser2020Taxes() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.get1099Income()).isEqualTo(85329.67);
    Assertions.assertThat(actual.getEffectiveTaxRate()).isEqualTo(0.3203);
    Assertions.assertThat(actual.getExpenseDeduction()).isEqualTo(15321.99);
    Assertions.assertThat(actual.getFederalIncomeTax()).isEqualTo(11556.64);
    Assertions.assertThat(actual.getFederalTaxOutstanding()).isEqualTo(21305.31);
    Assertions.assertThat(actual.getFederalTaxTotal()).isEqualTo(21305.31);
    Assertions.assertThat(actual.getFilingState()).isEqualTo("ca");
    Assertions.assertThat(actual.getFilingStatus()).isEqualTo(FilingStatus.SINGLE);
    Assertions.assertThat(actual.getIrsPayments()).isZero();
    Assertions.assertThat(actual.getMarginalTaxRate()).isEqualTo(0.4472);
    Assertions.assertThat(actual.getMedicareTax()).isEqualTo(1847.79);
    Assertions.assertThat(actual.getMileage()).isEqualTo(1761.4);
    Assertions.assertThat(actual.getMileageDeduction()).isEqualTo(1012.8);
    Assertions.assertThat(actual.getQbiDeduction()).isEqualTo(12824.11);
    Assertions.assertThat(actual.getQuarterlyPayments()).isZero();
    Assertions.assertThat(actual.getSelfEmploymentTax()).isEqualTo(9748.67);
    Assertions.assertThat(actual.getSmartTaxRate()).isEqualTo(0.3838);
    Assertions.assertThat(actual.getSocialSecurityTax()).isEqualTo(7900.88);
    Assertions.assertThat(actual.getStateIncomeTax()).isEqualTo(6026.71);
    Assertions.assertThat(actual.getStateTaxOutstanding()).isEqualTo(6026.71);
    Assertions.assertThat(actual.getStateTaxPayments()).isZero();
    Assertions.assertThat(actual.getTaxBalance()).isEqualTo(27332.02);
    Assertions.assertThat(actual.getTaxTotalOutstanding()).isEqualTo(27332.02);
    Assertions.assertThat(actual.getTaxWithholdings()).isZero();
    Assertions.assertThat(actual.getTaxWithholdingsPending()).isZero();
    Assertions.assertThat(actual.getTotalTax()).isEqualTo(27332.02);
    Assertions.assertThat(actual.getW2Income()).isEqualTo(60000);
    Assertions.assertThat(actual.getYear()).isEqualTo("2020");

    return this;
  }

  private TaxAssert(final Tax actual) {
    super(actual, TaxAssert.class);
  }
}
