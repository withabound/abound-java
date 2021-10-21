package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.users.UserRequest;
import com.withabound.models.users.UserResponse;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.UserResponseAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UsersTest extends AbstractAboundTest {
  public static final String TEST_USER_ID = "userId_test24b05d761ff58b5931bd07778c67b4e818e4";

  @Test
  public void testCreate() throws IOException {
    final String email = UUID.randomUUID().toString() + "@example.com";

    final UserRequest toCreate = UserRequest.builder().email(email).build();

    final AboundResponse<UserResponse> response = getAboundClient().users().create(toCreate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    assertThat(response.getData()).isNotNull();
    assertThat(response.getData().getUserId()).isEqualTo(TEST_USER_ID);
    assertThat(response.getData().getEmail().orElse(null)).isEqualTo(email);
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<UserResponse> response = getAboundClient().users().list();

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<UserResponse> users = response.getData();

    assertThat(users).hasSize(1);

    UserResponseAssert.assertThat(users.get(0)).isSamWilson();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<UserResponse> response = getAboundClient().users().retrieve(TEST_USER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    UserResponseAssert.assertThat(response.getData()).isSamWilson();
  }

  @Test
  public void testUpdate() throws IOException {
    final AboundResponse<UserResponse> original = getAboundClient().users().retrieve(TEST_USER_ID);
    AboundResponseAssert.assertThat(original).hasResponseMetadata();
    UserResponseAssert.assertThat(original.getData()).isSamWilson();

    final String newEmail = UUID.randomUUID().toString() + "@example.com";
    final UserResponse originalUser = original.getData();

    final UserRequest toUpdate = originalUser;
    toUpdate.setEmail(newEmail);

    final AboundResponse<UserResponse> updated =
        getAboundClient().users().update(originalUser.getUserId(), toUpdate);

    AboundResponseAssert.assertThat(updated).hasResponseMetadata();
    assertThat(updated.getData().getEmail().orElse(null)).isEqualTo(newEmail);
  }
}
