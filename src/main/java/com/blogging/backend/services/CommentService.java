package com.blogging.backend.services;

import com.blogging.backend.payloads.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}

