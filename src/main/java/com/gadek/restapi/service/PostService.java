package com.gadek.restapi.service;

import com.gadek.restapi.model.Post;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(Long postId);
    void removeById(Long postId);
    List<Post> findAllPost(int page, Sort.Direction sortDirection);
    List<Post> findAllPostWithComments(int page, Sort.Direction sortDirection);
    Post updatePost(Post post);
    Post save(Post post);
}
