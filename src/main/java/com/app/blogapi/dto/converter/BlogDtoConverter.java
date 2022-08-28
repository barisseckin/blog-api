package com.app.blogapi.dto.converter;

import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.model.Blog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogDtoConverter {

    public BlogDto convert(Blog blog) {
        return new BlogDto(
                blog.getTitle(),
                blog.getBody(),
                blog.getLikeNumber(),
                blog.getDislikeNumber(),
                blog.getCreateDate(),
                blog.getUpdateDate(),
                blog.getCategory().getName(),
                blog.getUser().getMail()
        );
    }

    public List<BlogDto> convert(List<Blog> blogs) {
        return blogs.stream().map(this::convert).collect(Collectors.toList());
    }
}
