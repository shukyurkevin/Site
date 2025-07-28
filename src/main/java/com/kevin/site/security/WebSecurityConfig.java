package com.kevin.site.security;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final JwtAuthenticationFilter jwtFilter;
  private final UnauthorizedHandler unauthorizedHandler;

  @Bean
  SecurityFilterChain webConfig(HttpSecurity http) throws Exception {
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .securityMatcher("/**")
        .sessionManagement(sessionManagementConfigurer
            -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(AbstractHttpConfigurer::disable)
        .exceptionHandling(h -> h.authenticationEntryPoint(unauthorizedHandler))
        .authorizeHttpRequests(registry -> registry
            .requestMatchers("/").permitAll()
            .requestMatchers("/api/v1/films/").permitAll()
            .requestMatchers("/api/v1/films/**").permitAll()
            .requestMatchers("/api/v1/films/onlyFilms").permitAll()
            .requestMatchers("/api/v1/films/onlyFilms/highestRated").permitAll()
            .requestMatchers("/api/v1/films/onlySeries").permitAll()
            .requestMatchers("/api/v1/films/onlySeries/highestRated").permitAll()
            .requestMatchers("/api/v1/films/highestRated").permitAll()
            .requestMatchers("/api/v1/films/search/**").permitAll()
            .requestMatchers("/api/v1/films/onlyFilms/mainGenres").permitAll()
            .requestMatchers("/api/v1/films/onlySeries/mainGenres").permitAll()
            .requestMatchers("/hello").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/register").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/api/v1/films/latest").permitAll()
            .requestMatchers("/refresh").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of(
        "https://onlyjar-production.up.railway.app",
        "http://localhost:3000",
        "http://localhost:8080",
        "http://localhost:5173"
    ));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of("Authorization", "Set-Cookie"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;

  }
}

