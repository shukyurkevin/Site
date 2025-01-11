package com.kevin.site.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserModel {

  @NotNull
  private Long userId;

  @NotNull(message = "Username is required")
  @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
  private String username;

  @NotNull
  @Size(min = 8,max = 20,message = "Password must be between 8 and 20 characters")
  @JsonIgnore
  private String password;

  @NotNull
  private String email;

  @NotNull
  private String roles;

  private String watchProgress;

  public String getWatchProgress() {
    return watchProgress;
  }

  public void setWatchProgress(String watchProgress) {
    this.watchProgress = watchProgress;
  }

  public Long getUserId() {
    return userId;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserModel() {
    super();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
