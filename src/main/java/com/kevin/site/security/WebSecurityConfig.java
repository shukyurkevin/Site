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
        .cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .securityMatcher("/**")
        .sessionManagement(sessionManagementConfigurer
            -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .formLogin(AbstractHttpConfigurer::disable)
        .exceptionHandling(h -> h.authenticationEntryPoint(unauthorizedHandler))
        .authorizeHttpRequests(registry -> registry
            .requestMatchers(("/login")).permitAll()
            .requestMatchers("/register").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*")); // Разрешить запросы от всех источников
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Разрешить основные HTTP-методы
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")); // Разрешить определенные заголовки
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

