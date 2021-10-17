package com.withabound.models.users;

/** Response body returned from the Abound Users APIs */
public class UserResponse extends UserRequest {
  private String userId;

  public UserResponse(final String email, final String foreignId, final UserProfile profile) {
    super(email, foreignId, profile);
  }

  public String getUserId() {
    return userId;
  }
}
