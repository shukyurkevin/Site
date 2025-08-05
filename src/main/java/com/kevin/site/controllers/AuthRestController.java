package com.kevin.site.controllers;

import com.kevin.site.dto.UserLoginDTO;
import com.kevin.site.dto.UserRegistrationDTO;
import com.kevin.site.entity.RefreshToken;
import com.kevin.site.entity.UserEntity;
import com.kevin.site.interfaces.UserRepository;
import com.kevin.site.security.jwt.JwtIssuer;
import com.kevin.site.security.UserPrincipal;
import com.kevin.site.services.AuthService;
import com.kevin.site.services.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class AuthRestController {
  private final AuthService authService;
  private final RefreshTokenService refreshTokenService;
  private final JwtIssuer jwtIssuer;
  private final UserRepository userRepository;

  public AuthRestController(AuthService authService, RefreshTokenService refreshTokenService, JwtIssuer jwtIssuer
      , UserRepository userRepository){
    this.authService = authService;
    this.refreshTokenService = refreshTokenService;
    this.jwtIssuer = jwtIssuer;
    this.userRepository = userRepository;
  }


  @GetMapping("/")
  public ResponseEntity<?> redirectToHello() {
    return ResponseEntity.status(302).header("Location", "/hello").build();
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    String htmlResponse = "<!DOCTYPE html>"
        + "<html lang=\"en\">"
        + "<head>"
        + "<meta charset=\"UTF-8\">"
        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
        + "<title>Hello, world!</title>"
        + "<style>"
        + "body {"
        + "    display: flex;"
        + "    justify-content: center;"
        + "    align-items: center;"
        + "    height: 100vh;"
        + "    margin: 0;"
        + "}"
        + "h1 {"
        + "    font-size: 96px;"
        + "    text-align: center;"
        + "}"
        + "</style>"
        + "</head>"
        + "<body>"
        + "<h1>Hello, BLUD!</h1>"
        + "</body>"
        + "</html>";
    return ResponseEntity.ok().body(htmlResponse);
  }
  @PostMapping("/register")
  public ResponseEntity<Map<String,String>> registerUser(@RequestBody UserRegistrationDTO userDto) {
    String result = authService.registerUser(userDto);

    if (result.equalsIgnoreCase("User registered successfully!"))
    {

      UserLoginDTO autologin =
          UserLoginDTO.builder().username(userDto.getUsername()).password(userDto.getPassword())
              .build();
      String token = authService.auth(autologin);
      UserEntity user = userRepository.findByUsername(autologin.getUsername());
      RefreshToken refreshToken;
      refreshToken = refreshTokenService.createBadRefreshToken(user.getId());

      ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
          .httpOnly(true)
          .sameSite("None")
          .secure(true)
          .path("/")
          .maxAge(Duration.ofDays(1))
          .build();
      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(Map.of(
              "accessToken", token,
              "message", "User registered successfully!"));
    }else{
      return ResponseEntity.status(409).body(Map.of("error", result));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String,String>> loginUser(@RequestBody UserLoginDTO userDto) {


    String token = authService.auth(userDto);
    if (token == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    if (token.equalsIgnoreCase("Wrong password")){return ResponseEntity.status(409)
        .body(Map.of("error", token));
    }
    UserEntity user = userRepository.findByUsername((userDto.getUsername()));
    if (user == null){
      user = userRepository.findByEmail(userDto.getUsername());
    }
    if (token.equalsIgnoreCase("Wrong username or email")){return ResponseEntity.status(409)
        .body(Map.of("error", token));
    }

    RefreshToken refreshToken;
    if (!userDto.isRememberMe()){
      refreshToken = refreshTokenService.createBadRefreshToken(user.getId());

      ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
          .httpOnly(true)
          .sameSite("None")
          .secure(true)
          .path("/")
          .maxAge(Duration.ofDays(1))
          .build();

      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(Map.of("accessToken", token));


    }else {
      refreshToken = refreshTokenService.createRefreshToken(user.getId());
    }
    System.out.println(refreshToken.getExpiryDate());

    ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
        .httpOnly(true)
        .sameSite("None")
        .secure(true)
        .path("/")
        .maxAge(Duration.ofDays(30))
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(Map.of("accessToken", token));

  }

  @PostMapping("/notIn")
  public ResponseEntity<Map<String, String>> logoutUser(
      @CookieValue(name = "refreshToken", required = false) String refreshToken,
      HttpServletResponse response) {

    if (refreshToken != null){
      System.out.println(refreshToken);
      refreshTokenService.deleteByToken(refreshToken);
    }

    ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
        .httpOnly(true)
        .sameSite("None")
        .secure(true)
        .path("/")
        .maxAge(0)
        .build();

    response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());

    return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
  }
  @GetMapping("/refresh")
  public ResponseEntity<Map<String, String>> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {

    if (refreshToken == null || !refreshTokenService.isValid(refreshToken)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Invalid or expired refresh token"));
    }

    RefreshToken tokenEntity = refreshTokenService.refreshTokenRepository.findByToken(refreshToken)
        .orElseThrow(() -> new RuntimeException("Refresh token not found"));

    UserEntity user = userRepository.findById(tokenEntity.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));

    String newAccessToken = jwtIssuer.issueAccessToken(user.getId(), user.getUsername(), List.of(user.getRoles()));

    return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
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
