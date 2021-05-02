package com.gadek.restapi.controller;

import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.service.PostService;
import com.gadek.restapi.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/")
    public List<Post> getPosts(@RequestParam(required = false, defaultValue = "0") int page){
        return postService.findAllPost(page);
    }

    public List<PostDTO> postToPostDTO(List<Post> postList){
      return  postList.stream()
                .map(TransformUtil::postToPostDTO).collect(Collectors.toList());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable long postId){
        throw new IllegalArgumentException("Not implemented yet");
    }

    @DeleteMapping("/posts")
    public ResponseEntity<Post> removePost(){
        throw new IllegalArgumentException("Not implemented yet");
    }
}
