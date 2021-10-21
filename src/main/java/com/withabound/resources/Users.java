package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.users.UserRequest;
import com.withabound.models.users.UserResponse;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/users */
public class Users extends AboundBaseResource<UserRequest, UserResponse> {
  @Override
  protected String getPath() {
    return "/users";
  }

  public Users(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, UserResponse.class);
  }

  public AboundResponse<UserResponse> create(final UserRequest toCreate) throws IOException {
    final Map<String, UserRequest> requestPayload = Collections.singletonMap("user", toCreate);

    return super.create(requestPayload);
  }

  @Override
  public AboundBulkResponse<UserResponse> list() throws IOException {
    return super.list();
  }

  @Override
  public AboundResponse<UserResponse> retrieve(final String userId) throws IOException {
    return super.retrieve(userId);
  }

  public AboundResponse<UserResponse> update(final String userId, final UserRequest toUpdate)
      throws IOException {
    final Map<String, UserRequest> requestPayload = Collections.singletonMap("user", toUpdate);

    return super.update(userId, requestPayload);
  }
}
