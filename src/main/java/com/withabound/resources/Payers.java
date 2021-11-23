package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.payers.Payer;
import com.withabound.models.payers.PayerRequest;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/payers */
public class Payers extends AboundBaseResource<PayerRequest, Payer> {
  public Payers(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Payer.class);
  }

  public AboundBulkResponse<Payer> create(final List<PayerRequest> toCreate) throws IOException {
    final Map<String, List<PayerRequest>> requestBody =
        Collections.singletonMap("payers", toCreate);

    return super.bulkCreate(requestBody);
  }

  @Override
  protected String getPath() {
    return "/payers";
  }

  @Override
  public AboundBulkResponse<Payer> list() throws IOException {
    return super.list();
  }

  @Override
  public AboundResponse<Payer> retrieve(final String payerId) throws IOException {
    return super.retrieve(payerId);
  }

  public AboundResponse<Payer> update(final String payerId, final PayerRequest toUpdate)
      throws IOException {
    final Map<String, PayerRequest> requestBody = Collections.singletonMap("payer", toUpdate);

    return super.update(payerId, requestBody);
  }

  @Override
  public AboundResponse<EmptyJsonObject> delete(final String payerId) throws IOException {
    return super.delete(payerId);
  }
}
