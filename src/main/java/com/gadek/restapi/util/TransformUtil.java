package com.gadek.restapi.util;

import com.gadek.restapi.dto.CommentDTO;
import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class TransformUtil {

    public static PostDTO postToPostDTO(Post post){
        return PostDTO.builder()
                .content(post.getContent())
                .id(post.getId())
                .title(post.getTitle())
                .created(post.getCreated())
                .commentDTOList(post.getComment() != null ? commentsToCommentDTO(post.getComment()) : null)
                .build();
    }

    public static CommentDTO commentToCommentDTO(Comment comment){
        return CommentDTO.builder()
                .content(comment.getContent())
                .id(comment.getId())
                .created(comment.getCreated())
                .postId(comment.getPostId().getId())
                .build();
    }

    public static List<PostDTO> postsToPostsDTO(List<Post> postList){
        return  postList.stream()
                .map(TransformUtil::postToPostDTO).collect(Collectors.toList());
    }

    public static List<CommentDTO> commentsToCommentDTO(List<Comment> commentList){
        return  commentList.stream()
                .map(TransformUtil::commentToCommentDTO).collect(Collectors.toList());
    }
}
