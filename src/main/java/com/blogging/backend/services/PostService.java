package com.blogging.backend.services;

import com.blogging.backend.payloads.PostDto;
import com.blogging.backend.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId );

    PostDto getPostById(Integer postId);
    PostResponse getAllPosts(Integer pageNumber , Integer pageSize, String sortBy, String sortDir);
    void deletePost(Integer postId);

    List<PostDto> getAllPostsByCategory(Integer categoryId);
    List<PostDto> getAllPostsByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}
