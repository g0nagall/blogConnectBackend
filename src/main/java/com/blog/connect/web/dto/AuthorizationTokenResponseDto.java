package com.blog.connect.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** AuthorizationTokenResponseDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationTokenResponseDto {
  private String authorizationToken;
}
