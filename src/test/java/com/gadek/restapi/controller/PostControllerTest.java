package com.gadek.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.repository.PostRepository;
import org.hamcrest.Matchers;
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
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
//        given
        Post newPost = new Post();
        newPost.setTitle("title");
        newPost.setContent("content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
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
}