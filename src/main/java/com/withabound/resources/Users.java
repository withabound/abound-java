package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.users.User;
import com.withabound.models.users.UserParams;
import com.withabound.models.users.UserRequest;
import com.withabound.resources.base.AboundBaseResource;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/users */
public class Users extends AboundBaseResource<UserRequest, User> {
  @Override
  protected String getPath() {
    return "/users";
  }

  public Users(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, User.class);
  }

  public AboundResponse<User> create(final UserRequest toCreate) throws IOException {
    final Map<String, UserRequest> requestPayload = Collections.singletonMap("user", toCreate);

    return super.create(requestPayload);
  }

  @Override
  public AboundBulkResponse<User> list() throws IOException {
    return super.list();
  }

  public AboundBulkResponse<User> list(final UserParams params) throws IOException {
    return super.list(params);
  }

  @Override
  public AboundResponse<User> retrieve(final String userId) throws IOException {
    return super.retrieve(userId);
  }

  public AboundResponse<User> update(final String userId, final UserRequest toUpdate)
      throws IOException {
    final Map<String, UserRequest> requestPayload = Collections.singletonMap("user", toUpdate);

    return super.update(userId, requestPayload);
  }
}
