package com.blogging.backend.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    @NotEmpty
    private String postTitle;

    private String postContent;

    private String postImageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();
}
