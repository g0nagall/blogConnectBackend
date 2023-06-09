package com.blog.connect.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** UserModel. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserModel {
  private Integer id;
  private String email;
  private String username;
  private String password;
  private LocalDateTime createdOn;
  private Boolean enabled;
}
