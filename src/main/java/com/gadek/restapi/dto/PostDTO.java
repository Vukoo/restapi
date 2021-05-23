package com.gadek.restapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gadek.restapi.response.ApiResponse;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "Response data messag", dataType = "date", example = "2021-12-14T12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime created;
    private List<CommentDTO> commentDTOList;
//    private ApiResponse apiResponse;
}
