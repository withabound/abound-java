package com.withabound.models.users;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/** Request body for creating or updating User resources */
@Setter
@Builder
public class UserRequest {
  private String email;
  private String foreignId;
  private JsonObject metadata;
  private JsonElement notes;
  private UserProfile profile;
  private UserBusiness business;

  public Optional<String> getEmail() {
    return Optional.ofNullable(email);
  }

  public Optional<String> getForeignId() {
    return Optional.ofNullable(foreignId);
  }

  public Optional<JsonObject> getMetadata() {
    return Optional.ofNullable(metadata);
  }

  public Optional<JsonElement> getNotes() {
    return Optional.ofNullable(notes);
  }

  public Optional<UserProfile> getProfile() {
    return Optional.ofNullable(profile);
  }

  public Optional<UserBusiness> getBusiness() {
    return Optional.ofNullable(business);
  }
}
