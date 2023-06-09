package com.blog.connect.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** AccountCredentialsRequestDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentialsRequestDto {
  @Email(
      message = "Email is not valid",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotEmpty(message = "Email cannot be empty")
  @Size(max = 32, message = "Email should not be more then 32 characters long")
  private String email;

  @NotEmpty(message = "Username cannot be empty")
  @Size(max = 32, message = "Username should not be more then 32 characters long")
  private String username;

  @NotEmpty(message = "Password cannot be empty")
  private String password;
}
