package com.gadek.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    @JsonProperty( required = true)
    @NotBlank(message = "Title may not be blank")
    private String title;
    @JsonProperty( required = true)
    @NotBlank(message = "Content may not be blank")
    private String content;
    private LocalDateTime created;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "postId", updatable = false, insertable = false)
    private List<Comment> comment;

}
