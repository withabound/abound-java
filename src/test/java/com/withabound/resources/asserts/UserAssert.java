package com.withabound.resources.asserts;

import com.withabound.models.users.User;
import com.withabound.models.users.UserProfile;
import com.withabound.util.TestUtils;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class UserAssert extends AbstractAssert<UserAssert, User> {
  public static UserAssert assertThat(final User actual) {
    return new UserAssert(actual);
  }

  public UserAssert isAdaLovelace() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    Assertions.assertThat(actual.getEmail()).isEqualTo(Optional.of("your_users_email@domain.com"));
    Assertions.assertThat(actual.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));

    final UserProfile profile = actual.getProfile().orElse(null);

    Assertions.assertThat(profile).isNotNull();
    Assertions.assertThat(profile.getFirstName()).isEqualTo(Optional.of("Ada"));
    Assertions.assertThat(profile.getLastName()).isEqualTo(Optional.of("Lovelace"));
    Assertions.assertThat(profile.getAddress()).isEqualTo(Optional.of("256 Byron Street"));
    Assertions.assertThat(profile.getAddress2()).isEqualTo(Optional.of("Suite 32"));
    Assertions.assertThat(profile.getCity()).isEqualTo(Optional.of("Palo Alto"));
    Assertions.assertThat(profile.getState()).isEqualTo(Optional.of("CA"));
    Assertions.assertThat(profile.getZipcode()).isEqualTo(Optional.of("94306"));
    Assertions.assertThat(profile.getPhoneNumber()).isEqualTo(Optional.of("6505551010"));
    Assertions.assertThat(profile.getDateOfBirth()).isEqualTo(Optional.of("1815-12-10"));
    Assertions.assertThat(profile.getIpAddress()).isEmpty();

    return this;
  }

  private UserAssert(final User actual) {
    super(actual, UserAssert.class);
  }
}
