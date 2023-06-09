package com.blog.connect.web.exceptions.exceptions;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/** AbstractAnteikuException. */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class AbstractOpenMindException extends RuntimeException {
  private HttpStatus httpStatus;
  private Map<String, String> errors;
  private String message;
}
