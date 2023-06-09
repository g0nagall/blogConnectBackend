package com.blog.connect.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentaryRequestDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentaryRequestDto {
  @NotEmpty(message = "Commentary cannot be empty")
  @Size(max = 200, message = "Commentary cannot be longer than 200 characters")
  private String commentary;

  @NotEmpty(message = "Post id cannot be empty")
  private String postId;
}
