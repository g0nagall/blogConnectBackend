package com.blog.connect.web.exceptions.exceptions;

import java.util.Map;
import org.springframework.http.HttpStatus;

/** UserNotActivatedException. */
public class UserException extends AbstractOpenMindException {

  public UserException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
