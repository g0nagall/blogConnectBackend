package com.blog.connect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Configuration class for password encoding. */
@Configuration
public class PasswordEncodingConfig {

  /**
   * PasswordEncoder bean.
   *
   * @return new BCryptPasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
