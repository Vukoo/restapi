package com.gadek.restapi.dto;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
