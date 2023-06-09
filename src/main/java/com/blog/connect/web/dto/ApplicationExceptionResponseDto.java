package com.blog.connect.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** ApplicationExceptionResponseDto. */
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ApplicationExceptionResponseDto {

  @EqualsAndHashCode.Exclude
  @JsonProperty("timestamp")
  private final String timestamp =
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

  @JsonProperty("status")
  private int status;

  @JsonProperty("message")
  private String message;

  @JsonProperty("errors")
  private Map<String, String> errors;
}
