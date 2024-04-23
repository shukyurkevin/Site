package com.kevin.site.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
public class LoginResponse {
  private final String accessToken;

  public LoginResponse(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public String toString() {
    return "accessToken='" + accessToken+"'";
  }
}
