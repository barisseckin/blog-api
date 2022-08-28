package com.app.blogapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateBlogRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @NotBlank
    private String categoryName;
    @Email
    private String userByMail;
}
