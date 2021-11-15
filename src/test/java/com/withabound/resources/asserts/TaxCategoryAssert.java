package com.withabound.resources.asserts;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TaxCategoryAssert extends AbstractAssert<TaxCategoryAssert, String[]> {
  public TaxCategoryAssert containsExact2020TaxCategories() {
    Assertions.assertThat(actual)
        .isNotNull()
        .isNotEmpty()
        .containsExactly(
            "Advertising and Marketing",
            "Car and Truck",
            "Commission and Fees",
            "Contract Labor",
            "Depletion",
            "Depreciation",
            "Employee Benefits Program",
            "Insurance",
            "Interest (Mortgage)",
            "Interest (Other)",
            "Legal and Professional Fees",
            "Office Expense",
            "Pension and Profit Sharing",
            "Rent (Vehicles and Equipment)",
            "Rent (Business Property)",
            "Repairs and Maintenance",
            "Supplies",
            "Taxes and Licenses",
            "Travel",
            "Meals",
            "Utilities",
            "Wages",
            "Other");

    return this;
  }

  public static TaxCategoryAssert assertThat(final String[] actual) {
    return new TaxCategoryAssert(actual);
  }

  private TaxCategoryAssert(final String[] actual) {
    super(actual, TaxCategoryAssert.class);
  }
}
