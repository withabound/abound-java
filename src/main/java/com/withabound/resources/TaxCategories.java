package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/tax-categories */
public class TaxCategories extends AboundBaseResource<Void, String[]> {
  public TaxCategories(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, String[].class);
  }

  @Override
  protected String getPath() {
    return "/taxCategories";
  }

  @Override
  public AboundResponse<String[]> retrieve(final String year) throws IOException {
    return super.retrieve(year);
  }
}
