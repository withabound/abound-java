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

  public UserAssert isSamWilson() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    Assertions.assertThat(actual.getEmail()).isEqualTo(Optional.of("your_users_email@domain.com"));
    Assertions.assertThat(actual.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));

    final UserProfile profile = actual.getProfile().orElse(null);

    Assertions.assertThat(profile).isNotNull();
    Assertions.assertThat(profile.getFirstName()).isEqualTo(Optional.of("Sam"));
    Assertions.assertThat(profile.getLastName()).isEqualTo(Optional.of("Wilson"));
    Assertions.assertThat(profile.getAddress()).isEqualTo(Optional.of("1500 Pennsylvania Ave NW"));
    Assertions.assertThat(profile.getAddress2()).isEqualTo(Optional.of("Suite 1776"));
    Assertions.assertThat(profile.getCity()).isEqualTo(Optional.of("Washington"));
    Assertions.assertThat(profile.getState()).isEqualTo(Optional.of("DC"));
    Assertions.assertThat(profile.getZipcode()).isEqualTo(Optional.of("20220"));
    Assertions.assertThat(profile.getPhoneNumber()).isEqualTo(Optional.of("2026229979"));
    Assertions.assertThat(profile.getDateOfBirth()).isEqualTo(Optional.of("1776-07-04"));
    Assertions.assertThat(profile.getIpAddress()).isEmpty();

    return this;
  }

  private UserAssert(final User actual) {
    super(actual, UserAssert.class);
  }
}
