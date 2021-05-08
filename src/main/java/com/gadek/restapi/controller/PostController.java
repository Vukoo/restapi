package com.gadek.restapi.controller;

import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.response.ApiResponse;
import com.gadek.restapi.service.CommonResponse;
import com.gadek.restapi.service.PostService;
import com.gadek.restapi.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//TODO: custom response with error handling
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String index(){
        return "Secured connection";
    }

    @GetMapping("/posts")
    public List<PostDTO> getPosts(@RequestParam(required = false, defaultValue = "0") int page,Sort.Direction sortDirection){
        final List<Post> allPost = postService.findAllPost(page, sortDirection != null ? sortDirection : Sort.Direction.ASC);
        return TransformUtil.postsToPostsDTO(allPost);
    }

    @GetMapping("/posts/comments")
    public List<PostDTO> getPostWithComments(@RequestParam(required = false, defaultValue = "0") int page,Sort.Direction sortDirection){
        final List<Post> allPostWithComments = postService.findAllPostWithComments(page, sortDirection != null ? sortDirection : Sort.Direction.ASC);
        return TransformUtil.postsToPostsDTO(allPostWithComments);
    }


    @GetMapping("/posts/{id}")
    public PostDTO getSinglePost(@PathVariable long id){
        Post post = postService.findById(id);
        return TransformUtil.postToPostDTO(post);
    }

    @PutMapping("/posts")
    public PostDTO updatePost(@RequestBody Post post){
        final Post postUpdated = postService.updatePost(post);
        return TransformUtil.postToPostDTO(postUpdated);
    }

    @DeleteMapping("/posts/{id}")
    public ApiResponse removePost(@PathVariable long id){
         postService.removeById(id);
        return CommonResponse.succResponse();
    }

//    TODO:validate post request
    @PostMapping("/posts")
    public PostDTO addPost(@RequestBody @Valid Post post){
         Post postSaved = postService.save(post);
        return TransformUtil.postToPostDTO(postSaved);
    }
}
