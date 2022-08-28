package com.app.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String mail;
    private boolean isActive;
    private LocalDate createDate;
    private LocalDate updateDate;
}
