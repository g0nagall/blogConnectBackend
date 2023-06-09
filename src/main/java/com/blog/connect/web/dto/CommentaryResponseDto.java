package com.blog.connect.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentaryResponseDto.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentaryResponseDto {
  private String id;
  private String commentary;
  private UserResponseDto creator;
  private String creatorId;
  private LocalDateTime createdOn;
}
