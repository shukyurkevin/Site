package com.kevin.site.entity;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("refresh_tokens")
public class RefreshToken {

  @Id
  @Column("id")
  private Long id;

  @Column("token")
  private String token;

  @Column("expiry_date")
  private Instant expiryDate;

  @Column("user_id")
  private Long userId;

  public RefreshToken() {
  }

  public RefreshToken(Long id, String token, Instant expiryDate, Long userId) {
    this.id = id;
    this.token = token;
    this.expiryDate = expiryDate;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Instant getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Instant expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
