package com.gadek.restapi.dto;

import com.gadek.restapi.response.ApiResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@Builder
@Data
public class PostDTO  {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private List<CommentDTO> commentDTOList;
//    private ApiResponse apiResponse;
}
