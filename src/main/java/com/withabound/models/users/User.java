package com.withabound.models.users;

/** Response body returned from the Abound Users APIs */
public class User extends UserRequest {
  private String userId;

  public User(final String email, final String foreignId, final UserProfile profile) {
    super(email, foreignId, profile);
  }

  public String getUserId() {
    return userId;
  }
}
