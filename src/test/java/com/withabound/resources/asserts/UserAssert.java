package com.withabound.resources.asserts;

import com.withabound.models.users.TaxClassification;
import com.withabound.models.users.User;
import com.withabound.models.users.UserBusiness;
import com.withabound.models.users.UserProfile;
import com.withabound.util.TestUtils;
import java.util.Optional;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class UserAssert extends AbstractAssert<UserAssert, User> {
  public static UserAssert assertThat(final User actual) {
    return new UserAssert(actual);
  }

  public UserAssert isBaseAdaLovelace() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getUserId()).isEqualTo(TestUtils.TEST_USER_ID);
    Assertions.assertThat(actual.getEmail()).isEqualTo(Optional.of("your_users_email@domain.com"));
    Assertions.assertThat(actual.getForeignId()).isEmpty();
    Assertions.assertThat(actual.getNotes()).isEmpty();

    return this;
  }

  public UserAssert isAdaLovelace() {
    isBaseAdaLovelace();

    final UserProfile profile = actual.getProfile().orElse(null);

    Assertions.assertThat(profile).isNotNull();
    Assertions.assertThat(profile.getFirstName()).isEqualTo(Optional.of("Ada"));
    Assertions.assertThat(profile.getLastName()).isEqualTo(Optional.of("Lovelace"));
    Assertions.assertThat(profile.getAddress()).isEqualTo(Optional.of("256 Byron Street"));
    Assertions.assertThat(profile.getAddress2()).isEqualTo(Optional.of("Suite 32"));
    Assertions.assertThat(profile.getCity()).isEqualTo(Optional.of("Palo Alto"));
    Assertions.assertThat(profile.getState()).isEqualTo(Optional.of("CA"));
    Assertions.assertThat(profile.getZipcode()).isEqualTo(Optional.of("94306"));
    Assertions.assertThat(profile.getCountry()).isEqualTo(Optional.of("US"));
    Assertions.assertThat(profile.getPhoneNumber()).isEqualTo(Optional.of("6505551010"));
    Assertions.assertThat(profile.getDateOfBirth()).isEqualTo(Optional.of("1815-12-10"));
    Assertions.assertThat(profile.getSocialSecurityNumber()).isEmpty();

    final UserBusiness business = actual.getBusiness().orElse(null);

    Assertions.assertThat(business).isNotNull();
    Assertions.assertThat(business.getEin()).isNull();
    Assertions.assertThat(business.getName()).isEqualTo("InGen Corporation");
    Assertions.assertThat(business.getTaxClassification())
        .isEqualTo(Optional.of(TaxClassification.C_CORPORATION));
    Assertions.assertThat(business.getAddress()).isEqualTo(Optional.of("100 Farallon Road"));
    Assertions.assertThat(business.getAddress2()).isEqualTo(Optional.of("Suite 501"));
    Assertions.assertThat(business.getCity()).isEqualTo(Optional.of("Palo Alto"));
    Assertions.assertThat(business.getState()).isEqualTo(Optional.of("CA"));
    Assertions.assertThat(business.getZipcode()).isEqualTo(Optional.of("94306"));
    Assertions.assertThat(business.getCountry()).isEqualTo(Optional.of("US"));

    return this;
  }

  private UserAssert(final User actual) {
    super(actual, UserAssert.class);
  }
}
