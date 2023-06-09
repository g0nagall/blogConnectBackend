package com.blog.connect.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.blog.connect.model.CommentaryModel;
import com.blog.connect.model.PostModel;
import com.blog.connect.model.UserModel;
import com.blog.connect.persistence.entity.CommentaryEntity;
import com.blog.connect.persistence.entity.PostEntity;
import com.blog.connect.persistence.repository.CommentaryRepository;
import com.blog.connect.persistence.repository.PostRepository;
import com.blog.connect.service.implementations.PostImpl;
import com.blog.connect.service.interfaces.PostInterface;
import com.blog.connect.service.interfaces.UserInterface;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    private PostInterface postService;
    @Mock private PostRepository postRepository;
    @Mock private CommentaryRepository commentaryRepository;
    @Mock private UserInterface userService;

    @BeforeEach
    public void init() {
        this.postService = new PostImpl(postRepository, commentaryRepository, userService);
    }

    @Test
    void testGetPostByIdReturnEmpty() {
        String mockPostId = "test";
        when(postRepository.findById(mockPostId)).thenReturn(Optional.empty());
        PostModel excpectedResult = new PostModel();
        PostModel actualResult = postService.getPostById(mockPostId);
        assertEquals(actualResult, excpectedResult);
    }

    @Test
    void testGetPostByIdReturnNonEmpty() {
        String mockContent = "MockContent";
        Integer mockPostId = 1;
        PostEntity mockPostEntity = PostEntity.builder().content(mockContent).id(mockPostId).build();
        when(postRepository.findById(mockPostId.toString())).thenReturn(Optional.of(mockPostEntity));
        PostModel excpectedResult =
                PostModel.builder().id(mockPostId.toString()).content(mockContent).build();
        PostModel actualResult = postService.getPostById(mockPostId.toString());
        assertEquals(actualResult, excpectedResult);
    }

    @Test
    void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(List.of());
        List<PostModel> actualResult = postService.getAllPosts();
        assertEquals(actualResult, List.of());
    }

    @Test
    void testCreatePost() {
        String mockContent = "MockContent";
        PostEntity mockEntity = PostEntity.builder().content(mockContent).build();
        Integer mockUserId = 1;
        UserModel mockUserModel = UserModel.builder().id(mockUserId).build();
        when(postRepository.save(any())).thenReturn(mockEntity);
        when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
        PostModel mockPost = PostModel.builder().content(mockContent).build();
        PostModel expectedResult = PostModel.builder().content(mockContent).build();
        PostModel actualResult = postService.createPost(mockPost);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void testCreatePostCommentary() {
        String mockCommentary = "mockCommentary";
        Integer mockUserId = 1;
        CommentaryEntity mockCommentaryEntity =
                CommentaryEntity.builder().commentary(mockCommentary).creatorId(mockUserId).build();
        CommentaryModel mockCommentaryModel =
                CommentaryModel.builder().commentary(mockCommentary).creatorId(mockUserId).build();
        UserModel mockUserModel = UserModel.builder().id(mockUserId).build();
        when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
        when(commentaryRepository.save(any())).thenReturn(mockCommentaryEntity);
        CommentaryModel expectedResult =
                CommentaryModel.builder().commentary(mockCommentary).creatorId(mockUserId).build();
        CommentaryModel actualResult = postService.createPostCommentary(mockCommentaryModel);
        assertEquals(actualResult, expectedResult);
    }
}
