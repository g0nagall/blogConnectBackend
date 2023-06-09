package com.blog.connect.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** CommentaryModel. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentaryModel {
  private String id;
  private String commentary;
  private UserModel creator;
  private Integer postId;
  private Integer creatorId;
  private LocalDateTime createdOn;
}
