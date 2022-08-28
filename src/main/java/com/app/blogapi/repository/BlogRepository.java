package com.app.blogapi.repository;

import com.app.blogapi.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Optional<Blog> findBlogByBody(String body);
}
