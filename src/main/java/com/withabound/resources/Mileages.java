package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.mileages.Mileage;
import com.withabound.models.mileages.MileageParams;
import com.withabound.models.mileages.MileageRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import com.withabound.resources.base.EmptyJsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

public class Mileages extends AboundUserScopedResource<MileageRequest, Mileage> {
  public Mileages(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Mileage.class);
  }

  @Override
  protected String getPath() {
    return "/mileage";
  }

  public AboundBulkResponse<Mileage> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  public AboundBulkResponse<Mileage> list(final String userId, final MileageParams params)
      throws IOException {
    return super.listForUser(userId, params);
  }

  public AboundBulkResponse<Mileage> create(
      final String userId, final List<MileageRequest> toCreate) throws IOException {
    final Map<String, List<MileageRequest>> requestPayload =
        Collections.singletonMap("mileages", toCreate);

    return super.bulkCreateForUser(userId, requestPayload);
  }

  public AboundResponse<Mileage> retrieve(final String userId, final String mileageId)
      throws IOException {
    return super.retrieveForUser(userId, mileageId);
  }

  public AboundResponse<Mileage> update(
      final String userId, final String mileageId, final MileageRequest toUpdate)
      throws IOException {
    final Map<String, MileageRequest> requestPayload =
        Collections.singletonMap("mileage", toUpdate);

    return super.updateForUser(userId, mileageId, requestPayload);
  }

  public AboundResponse<EmptyJsonObject> delete(final String userId, final String mileageId)
      throws IOException {
    return super.deleteForUser(userId, mileageId);
  }
}
