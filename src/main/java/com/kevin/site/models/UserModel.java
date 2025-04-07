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

  private String subscription = "free";

  private String reSub;

  private String pfpUrl = "https://devforum-uploads.s3.dualstack.us-east-2.amazonaws.com/uploads/original/4X/f/3/b/f3b335663c34170e3c64a8ff4ea692cb877ff832.jpeg";

  private String phoneNumber;

  public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }

  public String getReSub() {
    return reSub;
  }

  public void setReSub(String reSub) {
    this.reSub = reSub;
  }

  public String getPfpUrl() {
    return pfpUrl;
  }

  public void setPfpUrl(String pfpUrl) {
    this.pfpUrl = pfpUrl;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

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
