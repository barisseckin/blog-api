package com.app.blogapi.repository;

import com.app.blogapi.model.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Integer> {
}
