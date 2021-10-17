package com.withabound.models.users;

import lombok.Builder;
import lombok.Setter;

import java.util.Optional;

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
