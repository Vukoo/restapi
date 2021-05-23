package com.gadek.restapi.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Builder
@Data
public class PostResponse extends PageResponse {
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
