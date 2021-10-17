package com.withabound.models.users;

public class UserResponse extends UserRequest {
  private String userId;

  UserResponse(final String email, final String foreignId, final UserProfile profile) {
    super(email, foreignId, profile);
  }

  public String getUserId() {
    return userId;
  }
}
