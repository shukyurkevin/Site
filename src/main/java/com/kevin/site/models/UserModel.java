package com.kevin.site.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

  private String subscriptionEndDate;

  public UserModel() {
    super();
  }

}
