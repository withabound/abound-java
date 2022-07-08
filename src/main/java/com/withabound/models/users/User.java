package com.withabound.models.users;

import com.google.gson.JsonElement;

/** Response body returned from the Abound Users APIs */
public class User extends UserRequest {
  private String userId;

  public User(
      final String email,
      final String foreignId,
      final JsonElement notes,
      final UserProfile profile,
      final UserBusiness business) {
    super(email, foreignId, notes, profile, business);
  }

  public String getUserId() {
    return userId;
  }
}
