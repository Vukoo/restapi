package com.gadek.restapi.util;

import com.gadek.restapi.dto.PostDTO;
import com.gadek.restapi.model.Post;

public class TransformUtil {

    public static PostDTO postToPostDTO(Post post){
        return PostDTO.builder()
                .content(post.getContent())
                .id(post.getId())
                .title(post.getTitle())
                .created(post.getCreated())
                .build();

    }
}
