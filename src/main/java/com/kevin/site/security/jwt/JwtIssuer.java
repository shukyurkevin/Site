package com.kevin.site.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
  private final JwtProperties properties;


  public String issueAccessToken(Long userId, String username, List<String> roles){
    return JWT.create()
        .withSubject(String.valueOf(userId))
        .withExpiresAt(Instant.now().plus(Duration.of(5, ChronoUnit.MINUTES)))
        .withClaim("username", username)
        .withClaim("roles", roles)
        .sign(Algorithm.HMAC256(properties.getSecretKey()));

  }

}
