package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.mileages.Mileage;
import com.withabound.models.mileages.MileageRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
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
}
