package com.gadek.restapi.dto;

import com.gadek.restapi.response.ApiResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class PostDTO extends ApiResponse {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private List<CommentDTO> commentDTOList;
}
