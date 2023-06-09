package com.blog.connect.web.exceptions.exceptions;

import java.util.Map;
import org.springframework.http.HttpStatus;

/** IncorrectCredentialsException. */
public class CredentialsException extends AbstractOpenMindException {

  public CredentialsException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
