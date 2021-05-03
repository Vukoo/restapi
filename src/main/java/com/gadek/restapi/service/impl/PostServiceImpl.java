package com.gadek.restapi.service.impl;

import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.repository.CommentRepository;
import com.gadek.restapi.repository.PostRepository;
import com.gadek.restapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    public static final int PAGE_SIZE = 5;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

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

    @Override
    public List<Post> findAllPostWithComments(int page) {
        final List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE));
//        List<Long> postIdList = allPosts.stream()
//                .map(Post::getId).
//                collect(Collectors.toList());
        final List<Comment> commentsList = commentRepository.findAllByPostIdIn(allPosts);
        allPosts.forEach(post -> post.setComment(extractCommentS(post,commentsList)));

        return allPosts;
    }

    private List<Comment> extractCommentS(Post post, List<Comment> commentsList) {
       return commentsList.stream()
                .filter(a -> a.getPostId().equals(post))
                .collect(Collectors.toList());
    }
}
