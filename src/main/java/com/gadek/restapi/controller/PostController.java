package com.gadek.restapi.controller;

import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.service.PostService;
import com.gadek.restapi.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(required = false, defaultValue = "0") int page,Sort.Direction sortDirection){
        return postService.findAllPost(page,sortDirection != null ? sortDirection :Sort.Direction.ASC);
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostWithComments(@RequestParam(required = false, defaultValue = "0") int page,Sort.Direction sortDirection){
        return postService.findAllPostWithComments(page,sortDirection != null ? sortDirection :Sort.Direction.ASC  );
    }

    public List<PostDTO> postToPostDTO(List<Post> postList){
      return  postList.stream()
                .map(TransformUtil::postToPostDTO).collect(Collectors.toList());
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.findById(id);
    }

    @PutMapping("/posts")
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> removePost(@PathVariable long id){
         postService.removeById(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post){
        return postService.save(post);
    }
}
