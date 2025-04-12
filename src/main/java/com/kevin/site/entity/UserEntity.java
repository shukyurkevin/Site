package com.kevin.site.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@AllArgsConstructor
@ToString
public class UserEntity {

  @Id
  @Column("UserId")
  private Long id;
  @Column("Username")
  private String username;

  @Column("Password")
  @JsonIgnore
  private String password;

  @Column("Email")
  private String email;

  @Column("Roles")
  private String roles;

  @Column("watch_progress")
  private String watchProgress;

  @Column("pfp_Url")
  private String pfpUrl;

  @Column("re_sub")
  private String reSub;

  @Column("phone_number")
  private String phoneNumber;

  @Column("subscription")
  private String subscription;

  @Column("subscription_end_date")
  private String subscriptionEndDate;



  public UserEntity(){

  }

  public String getSubscriptionEndDate() {
    return subscriptionEndDate;
  }

  public void setSubscriptionEndDate(String subscriptionEndDate) {
    this.subscriptionEndDate = subscriptionEndDate;
  }

  public String getWatchProgress() {
    return watchProgress;
  }

  public void setWatchProgress(String watchProgress) {
    this.watchProgress = watchProgress;
  }


  public String getPfpUrl() {
    return pfpUrl;
  }

  public void setPfpUrl(String pfpUrl) {
    this.pfpUrl = pfpUrl;
  }

  public String getReSub() {
    return reSub;
  }

  public void setReSub(String reSub) {
    this.reSub = reSub;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
