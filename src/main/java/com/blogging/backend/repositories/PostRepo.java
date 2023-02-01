package com.blogging.backend.repositories;

import com.blogging.backend.entities.Category;
import com.blogging.backend.entities.Post;
import com.blogging.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List <Post> findByCategory(Category category);

    List <Post> findByPostTitleContaining(String postTitle);

}

