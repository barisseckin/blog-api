package com.app.blogapi.service;

import com.app.blogapi.dto.BlogCommentDto;
import com.app.blogapi.dto.converter.BlogCommentDtoConverter;
import com.app.blogapi.dto.request.CreateBlogCommentRequest;
import com.app.blogapi.exception.NotFoundException;
import com.app.blogapi.model.Blog;
import com.app.blogapi.model.BlogComment;
import com.app.blogapi.model.User;
import com.app.blogapi.repository.BlogCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogCommentService {

    private final BlogCommentRepository blogCommentRepository;
    private final BlogCommentDtoConverter blogCommentDtoConverter;
    private final UserService userService;
    private final BlogService blogService;

    public BlogCommentDto save(CreateBlogCommentRequest request) {
        User user = userService.getUserByMail(request.getUserByMail());
        Blog blog = blogService.getBlogByPublicId(request.getBlogPublicId());

        BlogComment blogComment = new BlogComment(
                request.getBody(),
                LocalDate.now(),
                blog,
                user
        );

        if (user.isActive()) {
            log.info("blog comment created");
            return blogCommentDtoConverter.convert(blogCommentRepository.save(blogComment));
        }

        return null;
    }

    public List<BlogCommentDto> getAll() {
        return blogCommentDtoConverter.convert(blogCommentRepository.findAll());
    }

    public void deleteByBody(String body) {
        BlogComment blogComment = getBlogCommentByBody(body);
        blogCommentRepository.deleteById(blogComment.getId());
        log.info("blog comment deleted, id: " + blogComment.getId());
    }

    private BlogComment getBlogCommentByBody(String body) {
        return blogCommentRepository.findBlogCommentByBody(body)
                .orElseThrow(() -> new NotFoundException("blog comment not found, body: " + body));
    }
}
