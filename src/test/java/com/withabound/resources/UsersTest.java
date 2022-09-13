package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.JsonObject;
import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.users.User;
import com.withabound.models.users.UserParams;
import com.withabound.models.users.UserRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.UserAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersTest extends AbstractAboundTest {
  @Test
  public void testCreate() throws IOException {
    final String email = TestUtils.randomEmail();

    final UserRequest toCreate = UserRequest.builder().email(email).build();

    final AboundResponse<User> response = getAboundClient().users().create(toCreate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    assertThat(response.getData()).isNotNull();
    assertThat(response.getData().getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    assertThat(response.getData().getEmail().orElse(null)).isEqualTo(email);
  }

  @Test
  public void testCreateWithMetadata() throws IOException {
    final JsonObject metadata = new JsonObject();
    metadata.addProperty("key", "value");

    final UserRequest toCreate = UserRequest.builder().metadata(metadata).build();

    final User created = getAboundClient().users().create(toCreate).getData();

    assertThat(created).isNotNull();
    assertThat(created.getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    final JsonObject createdMetadata = created.getMetadata().get();
    assertThat(createdMetadata.isJsonObject()).isTrue();
    assertThat(createdMetadata.getAsJsonObject().size()).isEqualTo(1);
    assertThat(createdMetadata.getAsJsonObject().get("key").getAsString()).isEqualTo("value");
  }

  @Test
  public void testCreateWithNullRequestBodyThrowsAboundApiException() {
    Assertions.assertThatThrownBy(() -> getAboundClient().users().create(null))
        .isInstanceOf(AboundApiException.class)
        .hasMessage("Missing user object in request (Code 1324d9e6)")
        .hasFieldOrPropertyWithValue("statusCode", 400)
        .hasFieldOrProperty("request");
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<User> response = getAboundClient().users().list();

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<User> users = response.getData();

    assertThat(users).hasSize(1);

    UserAssert.assertThat(users.get(0)).isBaseAdaLovelace();
  }

  @Test
  public void testListWithForeignIdParam() throws IOException, InterruptedException {
    final String foreignId = TestUtils.randomAlphabetic();
    final UserParams params = UserParams.builder().foreignId(foreignId).build();

    getMockAboundClient().users().list(params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("foreignId")).isEqualTo(foreignId);
  }

  @Test
  public void testListWithPageParam() throws IOException, InterruptedException {
    final String nextPage = TestUtils.randomAlphabetic();
    final UserParams params = UserParams.builder().page(nextPage).build();

    getMockAboundClient().users().list(params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
  }

  @Test
  public void testListWithManyParams() throws IOException, InterruptedException {
    final String nextPage = TestUtils.randomAlphabetic();
    final String foreignId = TestUtils.randomAlphabetic();
    final UserParams params = UserParams.builder().page(nextPage).foreignId(foreignId).build();

    getMockAboundClient().users().list(params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
    assertThat(requestUrl.queryParameter("foreignId")).isEqualTo(foreignId);
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<User> response =
        getAboundClient().users().retrieve(TestUtils.TEST_USER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    UserAssert.assertThat(response.getData()).isAdaLovelace();
  }

  @Test
  public void testUpdate() throws IOException {
    final AboundResponse<User> original =
        getAboundClient().users().retrieve(TestUtils.TEST_USER_ID);
    AboundResponseAssert.assertThat(original).hasResponseMetadata();
    UserAssert.assertThat(original.getData()).isAdaLovelace();

    final String newEmail = TestUtils.randomEmail();
    final User originalUser = original.getData();

    final UserRequest toUpdate = originalUser;
    toUpdate.setEmail(newEmail);

    final AboundResponse<User> updated =
        getAboundClient().users().update(originalUser.getUserId(), toUpdate);

    AboundResponseAssert.assertThat(updated).hasResponseMetadata();
    assertThat(updated.getData().getEmail().orElse(null)).isEqualTo(newEmail);
  }
}
