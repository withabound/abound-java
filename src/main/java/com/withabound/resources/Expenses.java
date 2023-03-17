package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.expenses.Expense;
import com.withabound.models.expenses.ExpenseParams;
import com.withabound.models.expenses.ExpenseRequest;
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
 * See https://docs.withabound.com/reference/expenses
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
public class Expenses extends AboundUserScopedResource<ExpenseRequest, Expense> {
  public Expenses(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Expense.class);
  }

  @Override
  protected String getPath() {
    return "/expenses";
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundBulkResponse<Expense> create(
      final String userId, final List<ExpenseRequest> toCreate) throws IOException {
    final Map<String, List<ExpenseRequest>> requestPayload =
        Collections.singletonMap("expenses", toCreate);

    return super.bulkCreateForUser(userId, requestPayload);
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
  public AboundBulkResponse<Expense> list(final String userId) throws IOException {
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
  public AboundBulkResponse<Expense> list(final String userId, final ExpenseParams params)
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
  public AboundResponse<Expense> retrieve(final String userId, final String expenseId)
      throws IOException {
    return super.retrieveForUser(userId, expenseId);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<Expense> update(
      final String userId, final String expenseId, final ExpenseRequest toUpdate)
      throws IOException {
    final Map<String, ExpenseRequest> requestPayload =
        Collections.singletonMap("expense", toUpdate);

    return super.updateForUser(userId, expenseId, requestPayload);
  }

  /**
   * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
   *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
   *     v2. For more detail on these product changes, what endpoints are changing in v3 and how
   *     that may affect your company, please view our <a
   *     href="https://docs.withabound.com/changelog">API Changelog</a>.
   */
  @Deprecated
  public AboundResponse<EmptyJsonObject> delete(final String userId, final String expenseId)
      throws IOException {
    return super.deleteForUser(userId, expenseId);
  }
}
