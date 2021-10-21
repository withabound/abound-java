package com.withabound.models.users;

import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/** Request body for creating or updating User resources */
@Setter
@Builder
public class UserRequest {
  private String email;
  private String foreignId;
  private UserProfile profile;

  public Optional<String> getEmail() {
    return Optional.ofNullable(email);
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }

  public Optional<UserProfile> getProfile() {
    return Optional.ofNullable(profile);
  }
}
