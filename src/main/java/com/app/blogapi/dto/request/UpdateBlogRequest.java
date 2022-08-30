package com.app.blogapi.dto.request;

import lombok.Data;

@Data
public class UpdateBlogRequest {
    private String title;
    private String body;
    private String categoryName;
}
