package com.app.blogapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateBlogCommentRequest {
    @NotBlank
    private String body;
    @NotBlank
    private int blogPublicId;
    @Email
    private String userByMail;
}
