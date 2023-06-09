package com.blog.connect.config;


import com.blog.connect.service.interfaces.TokenInterface;
import com.blog.connect.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Configuration class for web security. */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final UserInterface userInterface;
  private final TokenInterface tokenInterface;
  private final ExceptionHandlerForFilterConfig exceptionHandlerForFilterConfigConfiguration;

  /** Bean with SecurityFilterChain configuration. */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/api/posts/**")
        .authenticated()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/api/user/**")
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .addFilterBefore(
            new TokenFilterConfig(userInterface, tokenInterface),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(exceptionHandlerForFilterConfigConfiguration, TokenFilterConfig.class);
    return http.build();
  }

  /** Bean with WebSecurityCustomizer configuration. */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.ignoring()
            .requestMatchers(
                "/api/accounts/**",
                "/api/swagger-ui/**",
                "/api/v3/api-docs/**");
  }
}
