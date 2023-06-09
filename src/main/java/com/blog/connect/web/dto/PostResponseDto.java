package com.blog.connect.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostResponseDto. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class PostResponseDto {
  private String id;
  private UserResponseDto creator;
  private List<CommentaryResponseDto> commentaries;
  private String theme;
  private String content;
  private LocalDateTime createdOn;
}
