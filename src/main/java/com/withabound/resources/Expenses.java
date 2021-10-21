package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.expenses.Expense;
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

/** See https://docs.withabound.com/reference/expenses */
public class Expenses extends AboundUserScopedResource<ExpenseRequest, Expense> {
  public Expenses(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Expense.class);
  }

  @Override
  protected String getPath() {
    return "/expenses";
  }

  public AboundBulkResponse<Expense> bulkCreate(
      final String userId, final List<ExpenseRequest> toCreate) throws IOException {
    final Map<String, List<ExpenseRequest>> requestPayload =
        Collections.singletonMap("expenses", toCreate);

    return super.bulkCreateForUser(userId, requestPayload);
  }

  @Override
  public AboundBulkResponse<Expense> listForUser(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  @Override
  public AboundResponse<Expense> retrieveForUser(final String userId, final String expenseId)
      throws IOException {
    return super.retrieveForUser(userId, expenseId);
  }

  public AboundResponse<Expense> update(
      final String userId, final String expenseId, final ExpenseRequest toUpdate)
      throws IOException {
    final Map<String, ExpenseRequest> requestPayload =
        Collections.singletonMap("expense", toUpdate);

    return super.updateForUser(userId, expenseId, requestPayload);
  }

  @Override
  public AboundResponse<EmptyJsonObject> deleteForUser(final String userId, final String expenseId)
      throws IOException {
    return super.deleteForUser(userId, expenseId);
  }
}
