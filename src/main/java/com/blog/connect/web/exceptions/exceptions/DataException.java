package com.blog.connect.web.exceptions.exceptions;

import java.util.Map;
import org.springframework.http.HttpStatus;

/** DataNotFoundException. */
public class DataException extends AbstractOpenMindException {

  public DataException(Map<String, String> errors, String message) {
    super(HttpStatus.UNAUTHORIZED, errors, message);
  }
}
