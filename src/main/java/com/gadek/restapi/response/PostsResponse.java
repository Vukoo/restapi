package com.gadek.restapi.response;

import com.gadek.restapi.dto.PostDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class PostsResponse extends PageResponse {
    List<PostDTO> postDTOList;
}
