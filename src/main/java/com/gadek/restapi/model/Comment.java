package com.gadek.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    @JsonProperty( required = true)
    private String content;
    @JsonIgnore
    private LocalDateTime created;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "postId", insertable = true, updatable = true)
    private Post postId;
}
