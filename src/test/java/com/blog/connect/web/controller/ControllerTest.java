package com.blog.connect.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blog.connect.model.CommentaryModel;
import com.blog.connect.model.PostModel;
import com.blog.connect.service.interfaces.PostInterface;
import com.blog.connect.web.dto.CommentaryRequestDto;
import com.blog.connect.web.dto.CommentaryResponseDto;
import com.blog.connect.web.dto.PostRequestDto;
import com.blog.connect.web.dto.PostResponseDto;
import com.blog.connect.web.dto.UserPostsResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PostController.class)
public class ControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean private PostInterface postService;
    @Autowired private MockMvc mockMvc;

    @Test
    void getAllPostsTest() throws Exception {
        when(postService.getAllPosts()).thenReturn(List.of());
        MvcResult result = mockMvc.perform(get("/api/posts")).andExpect(status().isOk()).andReturn();
        UserPostsResponseDto resultDto =
                objectMapper.readValue(
                        result.getResponse().getContentAsString(), UserPostsResponseDto.class);
        assertEquals(resultDto, new UserPostsResponseDto(List.of()));
    }

    @Test
    void getAllPostByIdTest() throws Exception {
        String mockPostId = "mockId";
        PostModel mockPostModel = PostModel.builder().id(mockPostId).build();
        when(postService.getPostById(mockPostId)).thenReturn(mockPostModel);
        MvcResult result =
                mockMvc
                        .perform(get("/api/posts/{mockPostId}", mockPostId))
                        .andExpect(status().isOk())
                        .andReturn();
        PostResponseDto resultDto =
                objectMapper.readValue(result.getResponse().getContentAsString(), PostResponseDto.class);
        PostResponseDto expectedResult = PostResponseDto.builder().id(mockPostId).build();
        assertEquals(resultDto, expectedResult);
    }

    @Test
    void createPostTest() throws Exception {
        String mockContent = "mockContent";
        String mockTheme = "mockTheme";
        PostModel mockPostModel = PostModel.builder().content(mockContent).theme(mockTheme).build();
        when(postService.createPost(mockPostModel)).thenReturn(mockPostModel);
        PostRequestDto requestDto =
                PostRequestDto.builder().content(mockContent).theme(mockTheme).build();
        MvcResult result =
                mockMvc
                        .perform(
                                post("/api/posts")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(requestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
        PostResponseDto resultDto =
                objectMapper.readValue(result.getResponse().getContentAsString(), PostResponseDto.class);
        PostResponseDto expectedResult =
                PostResponseDto.builder().content(mockContent).theme(mockTheme).build();
        assertEquals(resultDto, expectedResult);
    }

    @Test
    void createNewPostCommentaryTest() throws Exception {
        String mockCommentary = "mockCommentary";
        Integer mockPostId = 1;
        CommentaryModel mockCommentaryModel =
                CommentaryModel.builder().commentary(mockCommentary).postId(mockPostId).build();
        when(postService.createPostCommentary(mockCommentaryModel)).thenReturn(mockCommentaryModel);
        CommentaryRequestDto requestDto =
                CommentaryRequestDto.builder()
                        .commentary(mockCommentary)
                        .postId(mockPostId.toString())
                        .build();
        MvcResult result =
                mockMvc
                        .perform(
                                post("/api/posts/commentary")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(requestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
        CommentaryResponseDto resultDto =
                objectMapper.readValue(
                        result.getResponse().getContentAsString(), CommentaryResponseDto.class);
        CommentaryResponseDto expectedResult =
                CommentaryResponseDto.builder().commentary(mockCommentary).build();
        assertEquals(resultDto, expectedResult);
    }
}