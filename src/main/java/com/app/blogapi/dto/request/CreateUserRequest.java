package com.app.blogapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {
    @NotBlank
    private String userName;
    @Email
    private String mail;
    @NotBlank
    private String password;
}
