package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.JsonObject;
import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.users.TaxClassification;
import com.withabound.models.users.User;
import com.withabound.models.users.UserBusiness;
import com.withabound.models.users.UserParams;
import com.withabound.models.users.UserProfile;
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
  public void testCreateUserWithProfileAndBusiness() throws IOException {
    final String email = TestUtils.randomEmail();

    final UserProfile profile =
        UserProfile.builder()
            .firstName("Erlich")
            .lastName("Bachman")
            .address("3338 Thunder Road")
            .city("Palo Alto")
            .state("CA")
            .zipcode("94306")
            .phoneNumber("8773427222")
            .dateOfBirth("1981-04-06")
            .build();

    final UserBusiness business =
        UserBusiness.builder()
            .ein("950361345")
            .name("Aviato Corporation")
            .dbaName("Aviato")
            .taxClassification(TaxClassification.C_CORPORATION)
            .address("3338 Thunder Road")
            .city("Palo Alto")
            .state("CA")
            .zipcode("94306")
            .build();

    final UserRequest toCreate =
        UserRequest.builder().email(email).profile(profile).business(business).build();

    final AboundResponse<User> response = getAboundClient().users().create(toCreate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    assertThat(response.getData()).isNotNull();
    assertThat(response.getData().getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    assertThat(response.getData().getEmail().orElse(null)).isEqualTo(email);

    final UserProfile profileResponse = response.getData().getProfile().orElse(null);
    assertThat(profileResponse).isNotNull();
    assertThat(profileResponse.getFirstName()).isEqualTo(profile.getFirstName());
    assertThat(profileResponse.getLastName()).isEqualTo(profile.getLastName());
    assertThat(profileResponse.getAddress()).isEqualTo(profile.getAddress());
    assertThat(profileResponse.getCity()).isEqualTo(profile.getCity());
    assertThat(profileResponse.getState()).isEqualTo(profile.getState());
    assertThat(profileResponse.getZipcode()).isEqualTo(profile.getZipcode());
    assertThat(profileResponse.getPhoneNumber()).isEqualTo(profile.getPhoneNumber());
    assertThat(profileResponse.getDateOfBirth()).isEqualTo(profile.getDateOfBirth());

    final UserBusiness businessResponse = response.getData().getBusiness().orElse(null);
    assertThat(businessResponse).isNotNull();
    assertThat(businessResponse.getName()).isEqualTo(business.getName());
    assertThat(businessResponse.getDbaName()).isEqualTo(business.getDbaName());
    assertThat(businessResponse.getTaxClassification()).isEqualTo(business.getTaxClassification());
    assertThat(businessResponse.getAddress()).isEqualTo(business.getAddress());
    assertThat(businessResponse.getCity()).isEqualTo(business.getCity());
    assertThat(businessResponse.getState()).isEqualTo(business.getState());
    assertThat(businessResponse.getZipcode()).isEqualTo(business.getZipcode());
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
        .hasMessage("Missing user object in request")
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
