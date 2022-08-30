package com.app.blogapi.service;

import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.dto.converter.BlogDtoConverter;
import com.app.blogapi.dto.request.CreateBlogRequest;
import com.app.blogapi.dto.request.UpdateBlogRequest;
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
import java.util.UUID;
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
                UUID.randomUUID().toString(),
                request.getTitle(),
                request.getBody(),
                LocalDate.now(),
                category,
                user
        );

        if (user.isActive()) {
            log.info("blog created, public id: " + blog.getPublicId());
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

    public BlogDto getByPublicId(String id) {
        return blogDtoConverter.convert(getBlogByPublicId(id));
    }

    public void deleteByPublicId(String id) {
        Blog blog = getBlogByPublicId(id);
        blogRepository.deleteById(blog.getId());
    }

    public BlogDto increaseLikeByPublicId(String id) {
        Blog blog = getBlogByPublicId(id);

        blog.setLikeNumber(blog.getLikeNumber() + 1);
        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    public BlogDto reduceLikeByPublicId(String id) {
        Blog blog = getBlogByPublicId(id);

        blog.setLikeNumber(blog.getLikeNumber() - 1);
        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    public BlogDto increaseDislikeByPublicId(String id) {
        Blog blog = getBlogByPublicId(id);

        blog.setDislikeNumber(blog.getDislikeNumber() + 1);
        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    public BlogDto reduceDislikeNumberByPublicId(String id) {
        Blog blog = getBlogByPublicId(id);

        blog.setDislikeNumber(blog.getDislikeNumber() - 1);
        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    public BlogDto update(String publicId, UpdateBlogRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategoryName());
        Blog blog = getBlogByPublicId(publicId);

        blog.setTitle(request.getTitle());
        blog.setBody(request.getBody());
        blog.setCategory(category);
        blog.setUpdateDate(LocalDate.now());

        return blogDtoConverter.convert(blogRepository.save(blog));
    }

    protected Blog getBlogByPublicId(String id) {
        return blogRepository.findBlogByPublicId(id)
                .orElseThrow(() -> new NotFoundException("blog not found, public id: " + id));
    }

}
