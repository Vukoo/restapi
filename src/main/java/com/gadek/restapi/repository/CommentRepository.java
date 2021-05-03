package com.gadek.restapi.repository;

import com.gadek.restapi.model.Comment;
import com.gadek.restapi.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {

    List<Comment> findAllByPostIdIn(List<Post> postId);
}
