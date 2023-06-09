package com.blog.connect.web.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserPostsResponseDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPostsResponseDto {
  List<PostResponseDto> userPosts;
}
