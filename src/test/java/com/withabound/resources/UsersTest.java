package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.users.User;
import com.withabound.models.users.UserRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.UserAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.List;
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
  public void testCreateWithNullRequestBodyThrowsAboundApiException() {
    Assertions.assertThatThrownBy(() -> getAboundClient().users().create(null))
        .isInstanceOf(AboundApiException.class)
        .hasMessage("Missing user object in request (Code 1324d9e6)")
        .hasFieldOrPropertyWithValue("statusCode", 400);
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<User> response = getAboundClient().users().list();

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<User> users = response.getData();

    assertThat(users).hasSize(1);

    UserAssert.assertThat(users.get(0)).isSamWilson();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<User> response =
        getAboundClient().users().retrieve(TestUtils.TEST_USER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    UserAssert.assertThat(response.getData()).isSamWilson();
  }

  @Test
  public void testUpdate() throws IOException {
    final AboundResponse<User> original =
        getAboundClient().users().retrieve(TestUtils.TEST_USER_ID);
    AboundResponseAssert.assertThat(original).hasResponseMetadata();
    UserAssert.assertThat(original.getData()).isSamWilson();

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
