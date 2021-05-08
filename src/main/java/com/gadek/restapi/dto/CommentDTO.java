package com.gadek.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gadek.restapi.model.Post;
import com.gadek.restapi.response.ApiResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class CommentDTO extends ApiResponse {
    private long id;
    private Long postId;
    private String content;
    private LocalDateTime created;
}
