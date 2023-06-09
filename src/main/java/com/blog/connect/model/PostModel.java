package com.blog.connect.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostModel. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostModel {
  private String id;
  private UserModel creator;
  private String theme;
  private String content;
  private List<CommentaryModel> commentaries;
  private LocalDateTime createdOn;
}
