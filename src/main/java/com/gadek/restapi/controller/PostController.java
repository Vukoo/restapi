package com.gadek.restapi.controller;

import com.gadek.restapi.dto.CommentDTO;
import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.response.ApiResponse;
import com.gadek.restapi.response.BaseResponse;
import com.gadek.restapi.response.PostsResponse;
import com.gadek.restapi.service.CommentService;
import com.gadek.restapi.service.PostService;
import com.gadek.restapi.util.TransformUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO: custom response with error handling
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/")
    public String index(){
        return "Secured connection";
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public PostsResponse getPosts(@RequestParam(required = false, defaultValue = "0") int page, Sort.Direction sortDirection){
        final Page<Post> allPosts = postService.findAllPost(page, sortDirection != null ? sortDirection : Sort.Direction.ASC);
        List<PostDTO> postDTOS = TransformUtil.postsToPostsDTO(allPosts.toList());
        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setPostDTOList(postDTOS);
        postsResponse.setPageDTO(TransformUtil.PAGE_TO_PAGE_DTO.apply(allPosts));
        BaseResponse.succResponse(postsResponse);
        return postsResponse;
    }

    @GetMapping("/posts/comments")
    public List<PostDTO> getPostWithComments(@RequestParam(required = false, defaultValue = "0") int page,Sort.Direction sortDirection) {
        final List<Post> allPostWithComments = postService.findAllPostWithComments(page, sortDirection != null ? sortDirection : Sort.Direction.ASC);
        return TransformUtil.postsToPostsDTO(allPostWithComments);
    }


    @GetMapping("/posts/{id}")
    public PostDTO getSinglePost(@PathVariable long id){
        Post post = postService.findById(id);
        return TransformUtil.postToPostDTO(post);
    }

    @PostMapping("/posts/{id}/comment")
    public CommentDTO addComment(@PathVariable long id, @RequestBody Comment comment){
        Post post = postService.findById(id);
        comment.setPostId(post);
        Comment commentDB = commentService.save(comment);
        CommentDTO commentDTO = TransformUtil.commentToCommentDTO(commentDB);
//        commentDTO.setApiResponse(BaseResponse.succResponse());
        return commentDTO;
    }

    @PutMapping("/posts")
    public PostDTO updatePost(@RequestBody Post post){
        final Post postUpdated = postService.updatePost(post);
        return TransformUtil.postToPostDTO(postUpdated);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse removePost(@PathVariable long id){
         postService.removeById(id);
        return BaseResponse.succResponse();
    }

//    TODO:validate post request
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDTO addPost(@RequestBody @Valid Post post){
         Post postSaved = postService.save(post);
        return TransformUtil.postToPostDTO(postSaved);
    }
}
