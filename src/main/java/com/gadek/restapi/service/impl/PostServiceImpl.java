package com.gadek.restapi.service.impl;

import com.gadek.restapi.model.Post;
import com.gadek.restapi.repository.PostRepository;
import com.gadek.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    public static final int PAGE_SIZE = 5;
    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    @Override
    public void removeById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> findAllPost(int page) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE));
    }
}
