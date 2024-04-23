package com.kevin.site.controllers;

import com.kevin.site.dto.UserLoginDTO;
import com.kevin.site.dto.UserRegistrationDTO;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.models.LoginResponse;
import com.kevin.site.models.UserModel;
import com.kevin.site.security.JwtIssuer;
import com.kevin.site.security.UserPrincipal;
import com.kevin.site.security.WebSecurityConfig;
import com.kevin.site.services.AuthService;
import com.kevin.site.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
  private final AuthService authService;
  final
  WebSecurityConfig webSecurityConfig;


  @GetMapping("/")
  public ResponseEntity<?> redirectToHello() {
    return ResponseEntity.status(302).header("Location", "/hello").build();
  }

  @GetMapping("/hello")
  public String hello() {
    return "HI NIGGER";
  }
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userDto) {
    return authService.registerUser(userDto);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@RequestBody UserLoginDTO userDto) {
    String token = authService.auth(userDto);
    if (token == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    return ResponseEntity.ok(new LoginResponse(token));
  }
  @GetMapping("/secured")
  public String secured(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return "If you see this, then you're logged in as " + userPrincipal.getUsername();
  }
  @GetMapping("/admin")
  public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal){
    return "if you see this, you're an admin "+userPrincipal.getUsername();
  }
}
