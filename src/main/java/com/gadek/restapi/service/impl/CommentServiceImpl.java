package com.gadek.restapi.service.impl;

import com.gadek.restapi.model.Comment;
import com.gadek.restapi.repository.CommentRepository;
import com.gadek.restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}
