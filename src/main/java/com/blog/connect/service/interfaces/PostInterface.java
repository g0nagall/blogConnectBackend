package com.blog.connect.service.interfaces;

import com.blog.connect.model.CommentaryModel;
import com.blog.connect.model.PostModel;
import java.util.List;

/** PostService. */
public interface PostInterface {
  PostModel getPostById(String postId);

  List<PostModel> getAllPosts();

  PostModel createPost(PostModel postModel);

  CommentaryModel createPostCommentary(CommentaryModel commentaryModel);
}
