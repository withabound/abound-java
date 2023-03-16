package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import okhttp3.OkHttpClient;

/**
 * See https://docs.withabound.com/reference/tax-categories
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
public class TaxCategories extends AboundBaseResource<Void, String[]> {
  public TaxCategories(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, String[].class);
  }

  @Override
  protected String getPath() {
    return "/taxCategories";
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  @Override
  public AboundResponse<String[]> retrieve(final String year) throws IOException {
    return super.retrieve(year);
  }
}
