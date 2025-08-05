package com.kevin.site.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kevin.site.security.UserPrincipal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtToPrincipalConverter {

  public UserPrincipal convert(DecodedJWT jwt){
    return UserPrincipal.builder()
        .id(Long.valueOf(jwt.getSubject()))
        .username(jwt.getClaim("username").asString())
        .authorities(extractAuthoritiesFromClaim(jwt))
        .build();
  }

  private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt){
    var claim = jwt.getClaim("roles");
    if (claim.isNull() || claim.isMissing())return List.of();
    return claim.asList(SimpleGrantedAuthority.class);
  }
}
