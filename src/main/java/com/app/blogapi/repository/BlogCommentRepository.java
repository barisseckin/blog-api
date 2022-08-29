package com.app.blogapi.repository;

import com.app.blogapi.model.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {
    Optional<BlogComment> findBlogCommentByBody(String body);
}
