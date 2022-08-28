package com.app.blogapi.service;

import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.dto.converter.BlogDtoConverter;
import com.app.blogapi.dto.request.CreateBlogRequest;
import com.app.blogapi.exception.NotFoundException;
import com.app.blogapi.model.Blog;
import com.app.blogapi.model.Category;
import com.app.blogapi.model.User;
import com.app.blogapi.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogDtoConverter blogDtoConverter;
    private final CategoryService categoryService;
    private final UserService userService;

    public BlogDto save(CreateBlogRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategoryName());
        User user = userService.getUserByMail(request.getUserByMail());

        Blog blog = new Blog(
                request.getTitle(),
                request.getBody(),
                LocalDate.now(),
                category,
                user
        );

        if (user.isActive()) {
            log.info("blog created, title: " + blog.getTitle());
            return blogDtoConverter.convert(blogRepository.save(blog));
        }

        log.warn("user not activate, user mail: " + request.getUserByMail());
        return null;
    }

    public List<BlogDto> getAll() {
        return blogDtoConverter.convert(blogRepository.findAll());
    }

    public List<BlogDto> getBlogByCategory(String categoryName) {
        return getAll().stream()
                .filter(blog -> blog.getCategoryName().equals(categoryName))
                .collect(Collectors.toList());
    }

    public List<BlogDto> searchBlogByTitle(String title) {
        return getAll().stream()
                .filter(blog -> blog.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public void deleteByBody(String body) {
        Blog blog = getBlogByBody(body);
        blogRepository.deleteById(blog.getId());
    }

    protected Blog getBlogByBody(String body) {
        return blogRepository.findBlogByBody(body)
                .orElseThrow(() -> new NotFoundException("blog not found, blog body: " + body));
    }

}
