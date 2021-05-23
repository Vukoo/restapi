package com.gadek.restapi.response;

import com.gadek.restapi.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse extends ApiResponse{
    @ApiModelProperty(value = "Page details")
    private PageDTO pageDTO;
}
