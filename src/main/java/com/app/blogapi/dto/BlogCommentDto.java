package com.app.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BlogCommentDto {
    private String body;
    private LocalDate createDate;
    private LocalDate updateDate;
    private BlogDto blogDto;
    private String userMail;
}
