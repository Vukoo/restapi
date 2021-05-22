package com.gadek.restapi.dto;

import com.gadek.restapi.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

public class PostsDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private List<CommentDTO> commentDTOList;
    private ApiResponse apiResponse;
}
