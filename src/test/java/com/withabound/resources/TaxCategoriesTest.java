package com.withabound.resources;

import com.withabound.AbstractAboundTest;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.TaxCategoryAssert;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class TaxCategoriesTest extends AbstractAboundTest {
  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<String[]> response = getAboundClient().taxCategories().retrieve("2020");

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final String[] taxCategories = response.getData();

    TaxCategoryAssert.assertThat(taxCategories).containsExact2020TaxCategories();
  }
}
