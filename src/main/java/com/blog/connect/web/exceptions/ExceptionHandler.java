package com.blog.connect.web.exceptions;

import com.blog.connect.web.dto.ApplicationExceptionResponseDto;
import com.blog.connect.web.exceptions.exceptions.AbstractOpenMindException;
import com.blog.connect.web.exceptions.exceptions.CredentialsException;
import com.blog.connect.web.exceptions.exceptions.DataException;
import com.blog.connect.web.exceptions.exceptions.TokenException;
import com.blog.connect.web.exceptions.exceptions.UserException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** ExceptionHandler. */
@RestControllerAdvice
public class ExceptionHandler {
  private static final Map<Map<String, String>, String> CONSTRAINS_MAP =
      Map.of(
          Map.of("email", "Email already exist"),
          "Ключ \"(email)=",
          Map.of("postId", "Post not found"),
          "Ключ (post_id)");

  /**
   * Exception handler.
   *
   * @param exception - MethodArgumentNotValidException.
   * @return ResponseEntity of ApplicationExceptionResponseDto.
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleException(
      MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : exception.getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    for (ObjectError objectError : exception.getGlobalErrors()) {
      errors.put(objectError.getObjectName(), objectError.getDefaultMessage());
    }
    return buildResponseDtoAndGetResponseEntity(
        "Input validation error", HttpStatus.UNPROCESSABLE_ENTITY, errors);
  }

  /**
   * Exception handler.
   *
   * @param exception DataIntegrityViolationException.
   * @return ApplicationExceptionResponseDto.
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApplicationExceptionResponseDto> handleException(
      DataIntegrityViolationException exception) {
    Map<String, String> errors = new HashMap<>();
    Throwable buff = exception.getRootCause();
    if (buff != null) {
      String rootMsg = buff.getMessage();
      if (rootMsg != null) {
        for (Map.Entry<Map<String, String>, String> entry : CONSTRAINS_MAP.entrySet()) {
          if (rootMsg.contains(entry.getValue())) {
            Map.Entry<String, String> errorsMap = entry.getKey().entrySet().iterator().next();
            errors.put(errorsMap.getKey(), errorsMap.getValue());
          }
        }
      }
      if (errors.isEmpty()) {
        errors.put("unknownError", "Unknown data operation error, please contact support");
      }
    }

    return buildResponseDtoAndGetResponseEntity(
        "Data operation error", HttpStatus.CONFLICT, errors);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler({
    CredentialsException.class,
    TokenException.class,
    UserException.class,
    DataException.class
  })
  public <T extends AbstractOpenMindException>
      ResponseEntity<ApplicationExceptionResponseDto> handleException(T exception) {
    return buildResponseDtoAndGetResponseEntity(
        exception.getMessage(), HttpStatus.UNAUTHORIZED, exception.getErrors());
  }

  private ResponseEntity<ApplicationExceptionResponseDto> buildResponseDtoAndGetResponseEntity(
      String message, HttpStatus status, Map<String, String> errors) {
    return ResponseEntity.status(status)
        .body(
            ApplicationExceptionResponseDto.builder()
                .message(message)
                .status(status.value())
                .errors(errors)
                .build());
  }
}
