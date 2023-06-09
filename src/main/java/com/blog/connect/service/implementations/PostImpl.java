package com.blog.connect.service.implementations;

import com.blog.connect.mapper.Mapper;
import com.blog.connect.model.CommentaryModel;
import com.blog.connect.model.PostModel;
import com.blog.connect.persistence.entity.CommentaryEntity;
import com.blog.connect.persistence.entity.PostEntity;
import com.blog.connect.persistence.repository.CommentaryRepository;
import com.blog.connect.persistence.repository.PostRepository;
import com.blog.connect.service.interfaces.PostInterface;
import com.blog.connect.service.interfaces.UserInterface;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** PostServiceImpl. */
@Service
@AllArgsConstructor
public class PostImpl implements PostInterface {
  private PostRepository postRepository;
  private CommentaryRepository commentaryRepository;
  private UserInterface userInterface;

  @Override
  public PostModel getPostById(String postId) {
    return Mapper.I.postEntityToModel(postRepository.findById(postId).orElse(new PostEntity()));
  }

  @Override
  public List<PostModel> getAllPosts() {
    return Mapper.I.postEntitiesToModels(postRepository.findAll());
  }

  @Override
  public PostModel createPost(PostModel postModel) {
    PostEntity postEntity =
        PostEntity.builder()
            .creatorId(userInterface.getAuthenticatedUser().getId())
            .theme(postModel.getTheme())
            .content(postModel.getContent())
            .createdOn(LocalDateTime.now())
            .build();
    return Mapper.I.postEntityToModel(postRepository.save(postEntity));
  }

  @Override
  public CommentaryModel createPostCommentary(CommentaryModel commentaryModel) {
    CommentaryEntity commentaryEntity = Mapper.I.commentaryModelToEntity(commentaryModel);
    commentaryEntity.setCreatorId(userInterface.getAuthenticatedUser().getId());
    commentaryEntity.setCreatedOn(LocalDateTime.now());
    return Mapper.I.commentaryEntityToModel(commentaryRepository.save(commentaryEntity));
  }
}
