package com.blog.connect.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostRequestDto. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRequestDto {
  @NotEmpty(message = "Theme cannot be empty")
  private String theme;

  @NotEmpty(message = "Content cannot be empty")
  private String content;
}
