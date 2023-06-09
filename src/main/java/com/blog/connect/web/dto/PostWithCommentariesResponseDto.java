package com.blog.connect.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostWithCommentariesResponseDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostWithCommentariesResponseDto {
  private String id;
  private UserResponseDto creator;
  private String theme;
  private String content;
  private LocalDateTime createdOn;
}
