package com.app.blogapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
