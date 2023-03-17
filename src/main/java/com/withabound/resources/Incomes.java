package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.incomes.Income;
import com.withabound.models.incomes.IncomeParams;
import com.withabound.models.incomes.IncomeRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import com.withabound.resources.base.EmptyJsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

/**
 * See https://docs.withabound.com/reference/incomes
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
public class Incomes extends AboundUserScopedResource<IncomeRequest, Income> {
  public Incomes(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Income.class);
  }

  @Override
  protected String getPath() {
    return "/incomes";
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundBulkResponse<Income> create(final String userId, final List<IncomeRequest> toCreate)
      throws IOException {
    final Map<String, List<IncomeRequest>> requestBody =
        Collections.singletonMap("incomes", toCreate);

    return super.bulkCreateForUser(userId, requestBody);
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
  public AboundBulkResponse<Income> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundBulkResponse<Income> list(final String userId, final IncomeParams params)
      throws IOException {
    return super.listForUser(userId, params);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<Income> retrieve(final String userId, final String incomeId)
      throws IOException {
    return super.retrieveForUser(userId, incomeId);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<Income> update(
      final String userId, final String incomeId, final IncomeRequest toUpdate) throws IOException {
    final Map<String, IncomeRequest> requestBody = Collections.singletonMap("income", toUpdate);

    return super.updateForUser(userId, incomeId, requestBody);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<EmptyJsonObject> delete(final String userId, final String incomeId)
      throws IOException {
    return super.deleteForUser(userId, incomeId);
  }
}
