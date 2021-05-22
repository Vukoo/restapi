package com.gadek.restapi.response;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Builder
public class PostResponse  {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private ApiResponse  apiResponse;
}
