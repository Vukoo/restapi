package com.gadek.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.repository.CommentRepository;
import com.gadek.restapi.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
//        given
        Post newPost = createPost();
//        when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + newPost.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
//        then
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final Post post = objectMapper.readValue(contentAsString, Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
    }

    private Post createPost() {
        Post newPost = new Post();
        newPost.setTitle("title");
        newPost.setContent("content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
        return newPost;
    }

    @Test
    @Transactional
    void shouldReturnCommentList() throws Exception {
        //        given
        final Post newPost = createPost();
        Comment comment1 = createComment(newPost);
        Comment comment2 = createComment(newPost);
        List<Comment> newCommentList = Arrays.asList(comment1,comment2);
        newPost.setComment(newCommentList);
        commentRepository.saveAll(newCommentList);
        //        when
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + newPost.getId() ))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();
        final PostDTO post = objectMapper.readValue(contentAsString, PostDTO.class);
        //        then
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
        assertThat(post.getCommentDTOList()).isNotNull();
        assertThat(post.getCommentDTOList().size()).isEqualTo(newCommentList.size());
        assertThat(post.getCommentDTOList().get(0).getContent()).isEqualTo(comment1.getContent());
        assertThat(post.getCommentDTOList().get(1).getContent()).isEqualTo(comment2.getContent());
    }

    private Comment createComment(Post post) {
        Comment comment = new Comment();
        comment.setContent("Content");
        comment.setCreated(LocalDateTime.now());
        comment.setPostId(post);
        return comment;
    }
}