package com.gadek.restapi.service.impl;

import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.exception.NotFoundException;
import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.repository.CommentRepository;
import com.gadek.restapi.repository.PostRepository;
import com.gadek.restapi.response.BaseResponse;
import com.gadek.restapi.response.CommentResponse;
import com.gadek.restapi.response.PostsResponse;
import com.gadek.restapi.service.PostService;
import com.gadek.restapi.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl extends CommonController implements PostService {


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Post> findAll() {

        return (List<Post>) postRepository.findAll();
    }

    @Override
    @Cacheable("SinglePost")
    public Post findById(Long postId)
    {
        return postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }

    @Override
    @CacheEvict(cacheNames = "SinglePost")
    public void removeById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Page<Post> findAllPost(int page, Sort.Direction sortDirection) {

        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(sortDirection, "id"));
        Page<Post> allPosts = postRepository.findAllPosts(pageRequest);
        return allPosts;
    }

    @Override
    @Cacheable(cacheNames = "PostsWithComments")
    public List<Post> findAllPostWithComments(int page, Sort.Direction sortDirection) {
        final Page<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE,Sort.by(sortDirection,"id")));
        final List<Comment> commentsList = commentRepository.findAllByPostIdIn(allPosts.getContent());
        allPosts.forEach(post -> post.setComment(extractCommentS(post,commentsList)));
        return allPosts.getContent();
    }

    @Override
    @CachePut(cacheNames = "SinglePost", key = "#result.id")
    public Post updatePost(Post post) {
        Post postDB = postRepository.findById(post.getPostId()).orElseThrow();
        postDB.setContent(post.getContent());
        postDB.setTitle(post.getTitle());
        return postDB;
    }

    @Override
    @Transactional
    public Post save(@RequestBody Post post) {
        return postRepository.save(post);
    }

    private List<Comment> extractCommentS(Post post, List<Comment> commentsList) {
       return commentsList.stream()
                .filter(a -> a.getPostId().equals(post))
                .collect(Collectors.toList());
    }
}
