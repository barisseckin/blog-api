package com.app.blogapi.dto.converter;

import com.app.blogapi.dto.BlogCommentDto;
import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.model.BlogComment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlogCommentDtoConverter {

    public BlogCommentDto convert(BlogComment blogComment) {
        return new BlogCommentDto(
                blogComment.getBody(),
                blogComment.getCreateDate(),
                blogComment.getUpdateDate(),
                new BlogDto(blogComment.getBlog().getTitle(), blogComment.getBlog().getBody(),
                        blogComment.getBlog().getLikeNumber(), blogComment.getBlog().getDislikeNumber(),
                        blogComment.getBlog().getCreateDate(), blogComment.getBlog().getUpdateDate(),
                        blogComment.getBlog().getCategory().getName(), blogComment.getBlog().getUser().getMail()),
                blogComment.getUser().getMail()
        );
    }

    public List<BlogCommentDto> convert(List<BlogComment> blogComments) {
        return blogComments.stream().map(this::convert).collect(Collectors.toList());
    }
}
