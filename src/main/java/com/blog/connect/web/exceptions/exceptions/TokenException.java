package com.blog.connect.web.exceptions.exceptions;

import java.util.Map;
import org.springframework.http.HttpStatus;

/** JwtAuthenticationException. */
public class TokenException extends AbstractOpenMindException {

  public TokenException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
