package com.blog.connect.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** UserResponseDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
  private String email;
  private String username;
  private LocalDateTime createdOn;
}
