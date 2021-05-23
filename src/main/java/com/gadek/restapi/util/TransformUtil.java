package com.gadek.restapi.util;

import com.gadek.restapi.dto.CommentDTO;
import com.gadek.restapi.dto.PageDTO;
import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.response.BaseResponse;
import com.gadek.restapi.response.PostsResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformUtil {



    public static PostDTO postToPostDTO(Post post){
        return PostDTO.builder()
                .content(post.getContent())
                .id(post.getPostId())
                .title(post.getTitle())
                .created(post.getCreated())
                .commentDTOList(post.getComment() != null && post.getComment().size() > 0 ? commentsToCommentDTO(post.getComment()) : null)
                .build();
    }

    public static CommentDTO commentToCommentDTO(Comment comment){
        return CommentDTO.builder()
                .content(comment.getContent())
                .id(comment.getCommentId())
                .created(comment.getCreated())
                .postId(comment.getPostId().getPostId())
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


    public static final Function<Page, PageDTO> PAGE_TO_PAGE_DTO = new Function<Page, PageDTO>() {
        @Override
        public PageDTO apply(Page page) {
            PageDTO pageDTO =  PageDTO.builder().
                    pageSize(page.getSize()).
                    totalPages(page.getTotalPages()).
                    totalRecord(page.getTotalElements()).
                    pageNumber(page.getNumber()).build();
            return pageDTO;
        }
    };

}
