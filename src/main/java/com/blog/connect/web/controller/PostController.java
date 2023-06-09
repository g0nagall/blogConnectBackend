package com.blog.connect.web.controller;

import com.blog.connect.mapper.Mapper;
import com.blog.connect.service.interfaces.PostInterface;
import com.blog.connect.web.dto.CommentaryRequestDto;
import com.blog.connect.web.dto.CommentaryResponseDto;
import com.blog.connect.web.dto.PostRequestDto;
import com.blog.connect.web.dto.PostResponseDto;
import com.blog.connect.web.dto.UserPostsResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** PostsController. */
@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
  private PostInterface postInterface;

  /**
   * getAllPosts.
   *
   * @return UserPostsResponse.
   */
  @GetMapping
  public ResponseEntity<UserPostsResponseDto> getAllPosts() {
    return ResponseEntity.ok(
        UserPostsResponseDto.builder()
            .userPosts(Mapper.I.postModelsToResponseDto(postInterface.getAllPosts()))
            .build());
  }

  @GetMapping("/{postId}")
  public ResponseEntity<PostResponseDto> getPostById(
      @PathVariable(value = "postId") String postId) {
    return ResponseEntity.ok(Mapper.I.postModelToResponseDto(postInterface.getPostById(postId)));
  }

  /**
   * createNewPost.
   *
   * @param requestDto PostRequest.
   * @return PostResponse.
   */
  @PostMapping
  public ResponseEntity<PostResponseDto> createNewPost(
      @Valid @RequestBody PostRequestDto requestDto) {
    return ResponseEntity.ok(
        Mapper.I.postModelToResponseDto(
            postInterface.createPost(Mapper.I.postRequestDtoToModel(requestDto))));
  }

  /**
   * createNewPostCommentary.
   *
   * @param requestDto CommentaryRequest.
   * @return CommentaryResponse.
   */
  @PostMapping("/commentary")
  public ResponseEntity<CommentaryResponseDto> createNewPostCommentary(
      @Valid @RequestBody CommentaryRequestDto requestDto) {
    return ResponseEntity.ok(
        Mapper.I.commentaryModelToResponseDto(
            postInterface.createPostCommentary(Mapper.I.commentaryRequestDtoToModel(requestDto))));
  }
}
